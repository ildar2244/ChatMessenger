package ru.axdar.chatmessenger.domain.account

import ru.axdar.chatmessenger.domain.interactor.UseCase
import ru.axdar.chatmessenger.domain.type.Either
import ru.axdar.chatmessenger.domain.type.Failure
import javax.inject.Inject

class Login @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<AccountEntity, Login.Params>() {

    data class Params(val email: String, val password: String)

    override suspend fun run(params: Params): Either<Failure, AccountEntity> =
        accountRepository.login(params.email, params.password)
}