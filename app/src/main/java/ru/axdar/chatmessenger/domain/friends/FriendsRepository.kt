package ru.axdar.chatmessenger.domain.friends

import ru.axdar.chatmessenger.domain.type.Either
import ru.axdar.chatmessenger.domain.type.Failure
import ru.axdar.chatmessenger.domain.type.None

interface FriendsRepository {
    fun getFriends(): Either<Failure, List<FriendEntity>>
    fun getFriendRequests(): Either<Failure, List<FriendEntity>>

    fun approveFriendRequest(friendEntity: FriendEntity): Either<Failure, None>
    fun cancelFriendRequest(friendEntity: FriendEntity): Either<Failure, None>

    fun addFriend(email: String): Either<Failure, None>
    fun deleteFriend(friendEntity: FriendEntity): Either<Failure, None>
}