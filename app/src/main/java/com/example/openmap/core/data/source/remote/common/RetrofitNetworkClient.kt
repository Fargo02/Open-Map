package com.example.openmap.core.data.source.remote.common

import android.util.Log
import com.example.openmap.core.util.NetworkError
import com.example.openmap.core.util.getConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException

abstract class RetrofitNetworkClient : NetworkClient {

    private val maxRetries = 3
    private val retryDelayMillis = 2000L

    override suspend fun <T> doRequest(request: suspend () -> T): Result<T> {

        if (!getConnected()) { return Result.failure(NetworkError.NoInternet()) }

        var currentAttempt = 0

        return withContext(Dispatchers.IO) {

            while (currentAttempt < maxRetries) {
                try {
                    return@withContext Result.success(request())
                } catch (e: retrofit2.HttpException) {
                    return@withContext when (e.code()) {
                        500 -> Result.failure(NetworkError.ServerError(e.message()))
                        404 -> Result.failure(NetworkError.NoData())
                        400 -> Result.failure(NetworkError.BadRequest())
                        else -> Result.failure(e)
                    }
                } catch (e: SocketTimeoutException) {
                    Log.e("SocketTimeoutException", "Попытка № $currentAttempt")
                    currentAttempt++
                    if (currentAttempt >= maxRetries) {
                        return@withContext Result.failure(
                            NetworkError.ServerError(e.message ?: "")
                        )
                    } else {
                        delay(retryDelayMillis)
                    }
                } catch (e: Exception) {
                    return@withContext Result.failure(NetworkError.ServerError(e.message ?: ""))
                }
            }
            Result.failure(NetworkError.ServerError(""))
        }
    }
}