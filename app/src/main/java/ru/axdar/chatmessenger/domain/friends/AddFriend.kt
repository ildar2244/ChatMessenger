package ru.axdar.chatmessenger.domain.friends

import ru.axdar.chatmessenger.domain.interactor.UseCase
import ru.axdar.chatmessenger.domain.type.Either
import ru.axdar.chatmessenger.domain.type.Failure
import ru.axdar.chatmessenger.domain.type.None
import javax.inject.Inject

class AddFriend @Inject constructor(
    private val friendsRepository: FriendsRepository
) : UseCase<None, AddFriend.Params>() {

    data class Params(val email: String)

    override suspend fun run(params: Params): Either<Failure, None> {
        return friendsRepository.addFriend(params.email)
    }
}