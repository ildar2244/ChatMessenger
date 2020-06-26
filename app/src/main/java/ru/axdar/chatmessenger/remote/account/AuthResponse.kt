package ru.axdar.chatmessenger.remote.account

import ru.axdar.chatmessenger.domain.account.AccountEntity
import ru.axdar.chatmessenger.remote.core.BaseResponse

class AuthResponse(
    success: Int,
    message: String,
    val user: AccountEntity
) : BaseResponse(success, message)