package ru.axdar.chatmessenger.ui.register

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_register.*
import ru.axdar.chatmessenger.R
import ru.axdar.chatmessenger.domain.account.AccountEntity
import ru.axdar.chatmessenger.domain.type.None
import ru.axdar.chatmessenger.presentation.viewmodel.AccountViewModel
import ru.axdar.chatmessenger.ui.App
import ru.axdar.chatmessenger.ui.core.BaseFragment
import ru.axdar.chatmessenger.ui.core.ext.onFailure
import ru.axdar.chatmessenger.ui.core.ext.onSuccess

class RegisterFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_register
    override val titleToolbar = R.string.register

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)

        accountViewModel = viewModel {
            onSuccess(registerData, ::handleRegister)
            onSuccess(accountData, ::handleLogin)
            onFailure(failureData, ::handleFailure)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNewMemberShip.setOnClickListener { register() }

        btnAlreadyHaveAkk.setOnClickListener {
            activity?.finish()
        }
    }

    private fun validateFields(): Boolean {
        val allFields = arrayOf(etEmail, etPassword, etConfirmPassword, etUsername)
        var allValid = true
        for (field in allFields) {
            allValid = field.testValidity() && allValid
        }
        return allValid && validatePassword()
    }

    private fun validatePassword(): Boolean {
        val valid = etPassword.text.toString() == etConfirmPassword.text.toString()
        if (!valid) {
            showMessage(getString(R.string.error_password_mismatch))
        }
        return valid
    }

    private fun register() {
        hideSoftKeyboard()
        val allValid = validateFields()

        if (allValid) {
            showProgress()

            accountViewModel.register(
                etEmail.text.toString(),
                etUsername.text.toString(),
                etPassword.text.toString()
            )
        }
    }

    private fun handleLogin(accountEntity: AccountEntity?) {
        hideProgress()
        activity?.let {
            navigator.showHome(it)
            it.finish()
        }
    }

    private fun handleRegister(none: None? = None()) {
        accountViewModel.login(etEmail.text.toString(), etPassword.text.toString())
    }
}