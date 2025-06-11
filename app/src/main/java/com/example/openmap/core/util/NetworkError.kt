package com.example.openmap.core.util

sealed class NetworkError(override val message: String) : Throwable(message = message) {

    class ServerError(message: String) :
        NetworkError("Ошибка при подключении к серверу: сообщение: $message")

    class NoData: NetworkError("Пустое тело ответа")

    class BadRequest : NetworkError("Ресурс не найден")

    class NoInternet : NetworkError("Нет подключения к интернету")
}