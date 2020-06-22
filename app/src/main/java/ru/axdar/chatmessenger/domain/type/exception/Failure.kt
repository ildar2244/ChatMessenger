package ru.axdar.chatmessenger.domain.type.exception

/**
 * base class for handling errors
 */

sealed class Failure {
    object NetworkConnectionError : Failure()
    object ServerError : Failure()
}