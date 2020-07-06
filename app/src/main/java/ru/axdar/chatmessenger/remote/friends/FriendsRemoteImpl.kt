package ru.axdar.chatmessenger.remote.friends

import ru.axdar.chatmessenger.data.friends.FriendsRemote
import ru.axdar.chatmessenger.domain.type.Either
import ru.axdar.chatmessenger.domain.type.Failure
import ru.axdar.chatmessenger.domain.type.None
import ru.axdar.chatmessenger.domain.type.friends.FriendEntity
import ru.axdar.chatmessenger.remote.core.Request
import ru.axdar.chatmessenger.remote.service.ApiService
import javax.inject.Inject

class FriendsRemoteImpl @Inject constructor(
    private val request: Request,
    private val service: ApiService
) : FriendsRemote {

    override fun getFriends(userId: Long, token: String): Either<Failure, List<FriendEntity>> {
        return request.make(service.getFriends(createGetFriendsMap(userId, token))) { it.friends }
    }

    override fun getFriendRequests(
        userId: Long,
        token: String
    ): Either<Failure, List<FriendEntity>> {
        return request.make(service.getFriendRequests(createGetFriendRequestsMap(userId, token))) { it.friendsRequest }
    }

    override fun approveFriendRequest(
        userId: Long,
        requestUserId: Long,
        friendsId: Long,
        token: String
    ): Either<Failure, None> {
        return request.make(service.approveFriendRequest(createApproveFriendRequestMap(userId, requestUserId, friendsId, token))) { None() }
    }

    override fun cancelFriendRequest(
        userId: Long,
        requestUserId: Long,
        friendsId: Long,
        token: String
    ): Either<Failure, None> {
        return request.make(service.cancelFriendRequest(createCancelFriendRequestMap(userId, requestUserId, friendsId, token))) { None() }
    }

    override fun addFriend(email: String, userId: Long, token: String): Either<Failure, None> {
        return request.make(service.addFriend(createAddFriendMap(email, userId, token))) { None() }
    }

    override fun deleteFriend(
        userId: Long,
        requestUserId: Long,
        friendsId: Long,
        token: String
    ): Either<Failure, None> {
        return request.make(service.deleteFriend(createDeleteFriendMap(userId, requestUserId, friendsId, token))) { None() }
    }

    private fun createGetFriendsMap(userId: Long, token: String): Map<String, String> {
        val map = HashMap<String, String>()
        map[ApiService.PARAM_USER_ID] = userId.toString()
        map[ApiService.PARAM_TOKEN] = token
        return map
    }

    private fun createGetFriendRequestsMap(userId: Long, token: String): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }

    private fun createApproveFriendRequestMap(
        userId: Long,
        requestUserId: Long,
        friendsId: Long,
        token: String
    ): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_REQUEST_USER_ID, requestUserId.toString())
        map.put(ApiService.PARAM_FRIENDS_ID, friendsId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }

    private fun createCancelFriendRequestMap(
        userId: Long,
        requestUserId: Long,
        friendsId: Long,
        token: String
    ): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_REQUEST_USER_ID, requestUserId.toString())
        map.put(ApiService.PARAM_FRIENDS_ID, friendsId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }

    private fun createAddFriendMap(email: String, userId: Long, token: String): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_EMAIL, email)
        map.put(ApiService.PARAM_REQUEST_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }

    private fun createDeleteFriendMap(userId: Long, requestUserId: Long, friendsId: Long, token: String): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_REQUEST_USER_ID, requestUserId.toString())
        map.put(ApiService.PARAM_FRIENDS_ID, friendsId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }
}