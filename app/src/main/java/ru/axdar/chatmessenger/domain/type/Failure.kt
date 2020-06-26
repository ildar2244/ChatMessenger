package ru.axdar.chatmessenger.domain.type

/**
 * base class for handling errors
 */

sealed class Failure {
    object NetworkConnectionError : Failure()
    object ServerError : Failure()
    object AuthError : Failure()
    object TokenError : Failure()

    object EmailAlreadyExistError : Failure()

    object NoSavedAccountError : Failure()
}