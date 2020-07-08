package ru.axdar.chatmessenger.ui.friends

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import ru.axdar.chatmessenger.R
import ru.axdar.chatmessenger.domain.friends.FriendEntity
import ru.axdar.chatmessenger.domain.type.None
import ru.axdar.chatmessenger.presentation.viewmodel.FriendsViewModel
import ru.axdar.chatmessenger.ui.App
import ru.axdar.chatmessenger.ui.core.BaseListFragment
import ru.axdar.chatmessenger.ui.core.ext.onFailure
import ru.axdar.chatmessenger.ui.core.ext.onSuccess

class FriendsFragment : BaseListFragment() {

    override val viewAdapter = FriendsAdapter()

    lateinit var friendsViewModel: FriendsViewModel

    override val titleToolbar = R.string.screen_friends

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        friendsViewModel = viewModel {
            onSuccess(friendsData, ::handleFriends)
            onSuccess(deleteFriendData, ::handleDeleteFriend)
            onFailure(failureData, ::handleFailure)
        }

        setOnItemClickListener { it, v ->
            (it as? FriendEntity)?.let {
                when (v.id) {
                    R.id.btnRemove -> showDeleteFriendDialog(it)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        showProgress()
        friendsViewModel.getFriends()
    }


    private fun showDeleteFriendDialog(entity: FriendEntity) {
        activity?.let {
            AlertDialog.Builder(it)
                .setMessage(getString(R.string.delete_friend))

                .setPositiveButton(android.R.string.yes) { dialog, which ->
                    friendsViewModel.deleteFriend(entity)
                }

                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }
    }


    private fun handleFriends(friends: List<FriendEntity>?) {
        hideProgress()
        if (friends != null) {
            viewAdapter.clear()
            viewAdapter.add(friends)
            viewAdapter.notifyDataSetChanged()
        }
    }

    private fun handleDeleteFriend(none: None?) {
        friendsViewModel.getFriends()
    }
}