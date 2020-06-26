package ru.axdar.chatmessenger.presentation

import ru.axdar.chatmessenger.cache.SharedPrefsManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Authenticator @Inject constructor(
    val sharedPrefsManager: SharedPrefsManager
) {
    fun userLoggedIn() = sharedPrefsManager.containsAnyAccount()
}