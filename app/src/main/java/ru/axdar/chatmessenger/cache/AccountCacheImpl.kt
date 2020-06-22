package ru.axdar.chatmessenger.cache

import ru.axdar.chatmessenger.data.account.AccountCache
import ru.axdar.chatmessenger.domain.type.Either
import ru.axdar.chatmessenger.domain.type.None
import ru.axdar.chatmessenger.domain.type.exception.Failure
import javax.inject.Inject

class AccountCacheImpl @Inject constructor(
    private val prefsManager: SharedPrefsManager
) : AccountCache {

    override fun getToken(): Either<Failure, String> {
        return prefsManager.getToken()
    }

    override fun saveToken(token: String): Either<Failure, None> {
        return prefsManager.saveToken(token)
    }
}