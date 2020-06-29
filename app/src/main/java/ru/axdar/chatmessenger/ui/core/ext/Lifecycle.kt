package ru.axdar.chatmessenger.ui.core.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import ru.axdar.chatmessenger.domain.type.HandleOnce
import ru.axdar.chatmessenger.domain.type.Failure

fun <T : Any, L : LiveData<T>> LifecycleOwner.onSuccess(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<HandleOnce<Failure>>> LifecycleOwner.onFailure(liveData: L, body: (Failure?) -> Unit) =
    liveData.observe(this, Observer {
        it.getContentIfNotHandled()?.let(body)
    })