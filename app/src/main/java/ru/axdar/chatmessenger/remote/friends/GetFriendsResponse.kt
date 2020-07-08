package ru.axdar.chatmessenger.remote.friends

import ru.axdar.chatmessenger.domain.friends.FriendEntity
import ru.axdar.chatmessenger.remote.core.BaseResponse

class GetFriendsResponse(
    success: Int,
    message: String,
    val friends: List<FriendEntity>
) : BaseResponse(success, message)