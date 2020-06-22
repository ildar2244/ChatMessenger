package ru.axdar.chatmessenger.domain.account

import ru.axdar.chatmessenger.domain.interactor.UseCase
import ru.axdar.chatmessenger.domain.type.None
import javax.inject.Inject

class UpdateToken @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<None, UpdateToken.Params>() {

    data class Params(val token: String)

    override suspend fun run(params: Params) = accountRepository.updateAccountToken(params.token)
}