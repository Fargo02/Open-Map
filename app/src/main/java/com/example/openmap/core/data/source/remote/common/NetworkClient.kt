package com.example.openmap.core.data.source.remote.common

interface NetworkClient {
    suspend fun <T> doRequest(request: suspend () -> T): Result<T>
}