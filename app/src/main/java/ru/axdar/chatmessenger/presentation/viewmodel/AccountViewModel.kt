package ru.axdar.chatmessenger.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import ru.axdar.chatmessenger.domain.account.*
import ru.axdar.chatmessenger.domain.type.None
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    val registerUseCase: Register,
    val loginUseCase: Login,
    val getAccountUseCase: GetAccount,
    val logoutUseCase: Logout
) : BaseViewModel() {

    var registerData: MutableLiveData<None> = MutableLiveData()
    var accountData: MutableLiveData<AccountEntity> = MutableLiveData()
    var logoutData: MutableLiveData<None> = MutableLiveData()

    fun register(email: String, name: String, password: String) {
        registerUseCase.invoke(Register.Params(email, name, password)) { it.either(::handleFailure, ::handleRegister) }
    }

    fun login(email: String, password: String) {
        loginUseCase.invoke(Login.Params(email, password)) {
            it.either(::handleFailure, ::handleAccount)
        }
    }

    fun getAccount() {
        getAccountUseCase(None()) {
            it.either(::handleFailure, ::handleAccount)
        }
    }

    fun logout() {
        logoutUseCase(None()) {
            it.either(::handleFailure, ::handleLogout)
        }
    }

    private fun handleRegister(none: None) {
        this.registerData.value = none
    }

    private fun handleAccount(accountEntity: AccountEntity) {
        this.accountData.value = accountEntity
    }

    private fun handleLogout(none: None) {
        this.logoutData.value = none
    }

    override fun onCleared() {
        super.onCleared()
        registerUseCase.unsubscribe()
        loginUseCase.unsubscribe()
        getAccountUseCase.unsubscribe()
        logoutUseCase.unsubscribe()
    }
}