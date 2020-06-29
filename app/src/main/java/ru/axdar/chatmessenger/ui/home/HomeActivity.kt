package ru.axdar.chatmessenger.ui.home

import ru.axdar.chatmessenger.R
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.navigation.*
import ru.axdar.chatmessenger.domain.account.AccountEntity
import ru.axdar.chatmessenger.domain.type.None
import ru.axdar.chatmessenger.presentation.viewmodel.AccountViewModel
import ru.axdar.chatmessenger.ui.App
import ru.axdar.chatmessenger.ui.core.BaseActivity
import ru.axdar.chatmessenger.ui.core.ext.onFailure
import ru.axdar.chatmessenger.ui.core.ext.onSuccess

class HomeActivity : BaseActivity() {

    override val fragment = ChatsFragment()

    override val contentId: Int = R.layout.activity_navigation

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)

        accountViewModel = viewModel {
            onSuccess(accountData, ::handleAccount)
            onSuccess(logoutData, ::handleLogout)
            onFailure(failureData, ::handleFailure)
        }

        accountViewModel.getAccount()

        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnLogout.setOnClickListener { accountViewModel.logout() }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawer(navigationView)
                } else {
                    drawerLayout.openDrawer(navigationView)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleAccount(accountEntity: AccountEntity?) {
        accountEntity?.let {
            tvUserName.text = it.name
            tvUseEmail.text = it.email
            tvUseStatus.text = it.status

            tvUseStatus.visibility = if (it.status.isNotEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun handleLogout(none: None?) {
        navigator.showMain(this)
        finish()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView)
        } else {
            super.onBackPressed()
        }
    }
}