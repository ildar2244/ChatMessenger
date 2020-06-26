package ru.axdar.chatmessenger.domain.account

import com.google.gson.annotations.SerializedName

class AccountEntity(
    @SerializedName("user_id")
    val id: String,
    val name: String,
    val email: String,
    @SerializedName("token")
    val token: String,
    val status: String,
    @SerializedName("user_date")
    val userDate: Long,
    val image: String
)