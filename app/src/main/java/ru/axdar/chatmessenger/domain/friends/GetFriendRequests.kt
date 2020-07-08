package ru.axdar.chatmessenger.domain.friends

import ru.axdar.chatmessenger.domain.interactor.UseCase
import ru.axdar.chatmessenger.domain.type.None
import javax.inject.Inject

class GetFriendRequests @Inject constructor(
    private val friendsRepository: FriendsRepository
) : UseCase<List<FriendEntity>, None>() {

    override suspend fun run(params: None) = friendsRepository.getFriendRequests()
}