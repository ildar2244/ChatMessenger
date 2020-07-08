package ru.axdar.chatmessenger.remote.friends

import com.google.gson.annotations.SerializedName
import ru.axdar.chatmessenger.domain.friends.FriendEntity
import ru.axdar.chatmessenger.remote.core.BaseResponse

class GetFriendRequestsResponse(
    success: Int,
    message: String,
    @SerializedName("friend_requests")
    val friendsRequest: List<FriendEntity>
) : BaseResponse(success, message)