package ru.axdar.chatmessenger.domain.media

import android.net.Uri
import ru.axdar.chatmessenger.domain.interactor.UseCase
import ru.axdar.chatmessenger.domain.type.None
import javax.inject.Inject

class CreateImageFile @Inject constructor(
    private val mediaRepository: MediaRepository
) : UseCase<Uri, None>() {

    override suspend fun run(params: None) = mediaRepository.createImageFile()
}