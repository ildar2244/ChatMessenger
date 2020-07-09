package ru.axdar.chatmessenger.domain.media

import android.graphics.Bitmap
import android.net.Uri
import ru.axdar.chatmessenger.domain.type.Either
import ru.axdar.chatmessenger.domain.type.Failure

interface MediaRepository {

    //при выборе пикчи используется для передачи в Intent камеры
    fun createImageFile(): Either<Failure, Uri>
    //кодирует изображние в строку; используется для отправки на сервер
    fun encodeImageBitmap(bitmap: Bitmap?): Either<Failure, String>
    //возвращает Bitmap используя путь Uri
    fun getPickedImage(uri: Uri?): Either<Failure, Bitmap>
}