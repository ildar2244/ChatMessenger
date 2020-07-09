package ru.axdar.chatmessenger.domain.media

import android.graphics.Bitmap
import ru.axdar.chatmessenger.domain.interactor.UseCase
import javax.inject.Inject

/**
 * для кодирования изображения в строку
 */
class EncodeImageBitmap @Inject constructor(
    private val mediaRepository: MediaRepository
) : UseCase<String, Bitmap>() {

    override suspend fun run(params: Bitmap) = mediaRepository.encodeImageBitmap(params)
}