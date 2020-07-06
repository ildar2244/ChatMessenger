package ru.axdar.chatmessenger.domain.type.friends

import ru.axdar.chatmessenger.domain.interactor.UseCase
import ru.axdar.chatmessenger.domain.type.Either
import ru.axdar.chatmessenger.domain.type.Failure
import ru.axdar.chatmessenger.domain.type.None
import javax.inject.Inject

class ApproveFriendRequest @Inject constructor(
    private val friendsRepository: FriendsRepository
) : UseCase<None, FriendEntity>() {

    override suspend fun run(params: FriendEntity): Either<Failure, None> {
        return friendsRepository.approveFriendRequest(params)
    }
}