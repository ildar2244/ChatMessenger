package ru.axdar.chatmessenger.ui.home

import ru.axdar.chatmessenger.R
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.navigation.*
import ru.axdar.chatmessenger.domain.account.AccountEntity
import ru.axdar.chatmessenger.domain.friends.FriendEntity
import ru.axdar.chatmessenger.domain.type.Failure
import ru.axdar.chatmessenger.domain.type.None
import ru.axdar.chatmessenger.presentation.viewmodel.AccountViewModel
import ru.axdar.chatmessenger.presentation.viewmodel.FriendsViewModel
import ru.axdar.chatmessenger.ui.App
import ru.axdar.chatmessenger.ui.core.BaseActivity
import ru.axdar.chatmessenger.ui.core.BaseFragment
import ru.axdar.chatmessenger.ui.core.ext.onFailure
import ru.axdar.chatmessenger.ui.core.ext.onSuccess
import ru.axdar.chatmessenger.ui.friends.FriendRequestsFragment
import ru.axdar.chatmessenger.ui.friends.FriendsFragment
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    override var fragment: BaseFragment = ChatsFragment()

    override val contentId: Int = R.layout.activity_navigation

    private lateinit var accountViewModel: AccountViewModel

    @Inject
    lateinit var friendsViewModel: FriendsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        App.appComponent.inject(this)

        accountViewModel = viewModel {
            onSuccess(accountData, ::handleAccount)
            onSuccess(logoutData, ::handleLogout)
            onFailure(failureData, ::handleFailure)
        }

        friendsViewModel = viewModel {
            onSuccess(addFriendData, ::handleAddFriend)
            onSuccess(friendRequestsData, ::handleFriendRequests)
            onFailure(failureData, ::handleFailure)
        }

        accountViewModel.getAccount()

        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnLogout.setOnClickListener { accountViewModel.logout() }

        btnChats.setOnClickListener {
            replaceFragment(ChatsFragment())
            closeDrawer()
        }

        btnAddFriend.setOnClickListener {
            if (containerAddFriend.visibility == View.VISIBLE) {
                containerAddFriend.visibility = View.GONE
            } else {
                containerAddFriend.visibility = View.VISIBLE
            }
        }

        btnAdd.setOnClickListener {
            hideSoftKeyboard()
            showProgress()
            friendsViewModel.addFriend(etEmail.text.toString())
        }

        btnFriends.setOnClickListener {
            replaceFragment(FriendsFragment())
            closeDrawer()
        }

        supportFragmentManager.beginTransaction().replace(R.id.requestContainer, FriendRequestsFragment()).commit()

        btnRequests.setOnClickListener {
            friendsViewModel.getFriendRequests()


            if (requestContainer.visibility == View.VISIBLE) {
                requestContainer.visibility = View.GONE
            } else {
                requestContainer.visibility = View.VISIBLE
            }
        }
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

    private fun closeDrawer() {
        hideSoftKeyboard()
        drawerLayout.closeDrawer(navigationView)
    }

    private fun handleAccount(accountEntity: AccountEntity?) {
        accountEntity?.let {
            tvUserName.text = it.name
            tvUserEmail.text = it.email
            tvUserStatus.text = it.status

            tvUserStatus.visibility = if (it.status.isNotEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun handleLogout(none: None?) {
        navigator.showMain(this)
        finish()
    }

    private fun handleAddFriend(none: None?) {
        etEmail.text.clear()
        containerAddFriend.visibility = View.GONE

        hideProgress()
        showMessage("Запрос отправлен.")
    }

    private fun handleFriendRequests(requests: List<FriendEntity>?) {
        if (requests?.isEmpty() == true) {
            requestContainer.visibility = View.GONE
            if (drawerLayout.isDrawerOpen(navigationView)) {
                showMessage("Нет входящих приглашений.")
            }
        }
    }

    override fun handleFailure(failure: Failure?) {
        hideProgress()
        when (failure) {
            Failure.ContactNotFoundError -> showEmailNotFoundDialog()
            else -> super.handleFailure(failure)
        }
    }

    private fun showEmailNotFoundDialog() {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.message_promt_app))

            .setPositiveButton(android.R.string.yes) { dialog, which ->
                navigator.showEmailInvite(this, etEmail.text.toString())
            }

            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            hideSoftKeyboard()
            drawerLayout.closeDrawer(navigationView)
        } else {
            super.onBackPressed()
        }
    }
}