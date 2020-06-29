package ru.axdar.chatmessenger.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_login.*
import ru.axdar.chatmessenger.R
import ru.axdar.chatmessenger.domain.account.AccountEntity
import ru.axdar.chatmessenger.presentation.viewmodel.AccountViewModel
import ru.axdar.chatmessenger.ui.App
import ru.axdar.chatmessenger.ui.core.BaseFragment
import ru.axdar.chatmessenger.ui.core.ext.onFailure
import ru.axdar.chatmessenger.ui.core.ext.onSuccess

class LoginFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_login
    override val titleToolbar: Int = R.string.screen_login

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)

        accountViewModel = viewModel {
            onSuccess(accountData, ::renderAccount)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener { validateFields() }

        btnRegister.setOnClickListener {
            activity?.let { navigator.showSignUp(it) }
        }
    }

    private fun validateFields() {
        hideSoftKeyboard()
        val allFields = arrayOf(etEmail, etPassword)
        var allValid = true
        for (field in allFields) {
            allValid = field.testValidity() && allValid
        }
        if (allValid)
            login(etEmail.text.toString(), etPassword.text.toString())
    }

    private fun login(email: String, password: String) {
        showProgress()
        accountViewModel.login(email, password)
    }

    private fun renderAccount(account: AccountEntity?) {
        hideProgress()
        activity?.let {
            navigator.showHome(it)
            it.finish()
        }
    }
}