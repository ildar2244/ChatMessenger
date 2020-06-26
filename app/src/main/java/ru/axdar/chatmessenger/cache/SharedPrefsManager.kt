package ru.axdar.chatmessenger.cache

import android.content.SharedPreferences
import ru.axdar.chatmessenger.domain.account.AccountEntity
import ru.axdar.chatmessenger.domain.type.Either
import ru.axdar.chatmessenger.domain.type.None
import ru.axdar.chatmessenger.domain.type.Failure
import javax.inject.Inject

class SharedPrefsManager @Inject constructor(
    private val prefs: SharedPreferences
) {
    companion object {
        const val ACCOUNT_TOKEN = "account_token"
        const val ACCOUNT_ID = "account_id"
        const val ACCOUNT_NAME = "account_name"
        const val ACCOUNT_EMAIL = "account_email"
        const val ACCOUNT_STATUS = "account_status"
        const val ACCOUNT_DATE = "account_date"
        const val ACCOUNT_IMAGE = "account_image"
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

    fun saveAccount(accountEntity: AccountEntity): Either<Failure, None> {
        prefs.edit().apply {
            putLong(ACCOUNT_ID, accountEntity.id)
            putString(ACCOUNT_NAME, accountEntity.name)
            putString(ACCOUNT_EMAIL, accountEntity.email)
            putString(ACCOUNT_TOKEN, accountEntity.token)
            putString(ACCOUNT_STATUS, accountEntity.status)
            putLong(ACCOUNT_DATE, accountEntity.userDate)
            putString(ACCOUNT_IMAGE, accountEntity.image)
        }.apply()

        return Either.Right(None())
    }

    fun getAccount(): Either<Failure, AccountEntity> {
        val id = prefs.getLong(ACCOUNT_ID, 0)

        if (id == 0L)
            return Either.Left(Failure.NoSavedAccountError)

        val account = AccountEntity(
            prefs.getLong(ACCOUNT_ID, 0),
            prefs.getString(ACCOUNT_NAME, "")!!,
            prefs.getString(ACCOUNT_EMAIL, "")!!,
            prefs.getString(ACCOUNT_TOKEN, "")!!,
            prefs.getString(ACCOUNT_STATUS, "")!!,
            prefs.getLong(ACCOUNT_DATE, 0),
            prefs.getString(ACCOUNT_IMAGE, "")!!
        )

        return Either.Right(account)
    }

    fun removeAccount(): Either<Failure, None> {
        prefs.edit().apply {
            remove(ACCOUNT_ID)
            remove(ACCOUNT_NAME)
            remove(ACCOUNT_EMAIL)
            remove(ACCOUNT_TOKEN)
            remove(ACCOUNT_STATUS)
            remove(ACCOUNT_DATE)
            remove(ACCOUNT_IMAGE)
        }.apply()

        return Either.Right(None())
    }

    fun containsAnyAccount(): Boolean {
        val id = prefs.getLong(ACCOUNT_ID, 0)
        return id != 0L
    }
}