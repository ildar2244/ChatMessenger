package ru.axdar.chatmessenger.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.axdar.chatmessenger.domain.type.HandleOnce
import ru.axdar.chatmessenger.domain.type.Failure

abstract class BaseViewModel : ViewModel() {

    var failureData: MutableLiveData<HandleOnce<Failure>> = MutableLiveData()
    var progressData: MutableLiveData<Boolean> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failureData.value = HandleOnce(failure)
    }

    protected fun updateProgress(progress: Boolean) {
        this.progressData.value = progress
    }
}