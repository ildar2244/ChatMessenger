package ru.axdar.chatmessenger.cache

import android.content.SharedPreferences
import ru.axdar.chatmessenger.domain.type.Either
import ru.axdar.chatmessenger.domain.type.None
import ru.axdar.chatmessenger.domain.type.exception.Failure
import javax.inject.Inject

class SharedPrefsManager @Inject constructor(
    private val prefs: SharedPreferences
) {
    companion object {
        const val ACCOUNT_TOKEN = "account token"
    }

    fun saveToken(token: String): Either<Failure, None> {
        prefs.edit().apply {
            putString(ACCOUNT_TOKEN, token)
        }.apply()

        return Either.Right(None())
    }

    fun getToken(): Either.Right<String> {
        return Either.Right(prefs.getString(ACCOUNT_TOKEN, "")!!)
    }
}