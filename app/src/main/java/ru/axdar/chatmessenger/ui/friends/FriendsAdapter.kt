package ru.axdar.chatmessenger.ui.friends

import android.view.View
import kotlinx.android.synthetic.main.item_friend.view.*
import ru.axdar.chatmessenger.ui.core.BaseAdapter
import ru.axdar.chatmessenger.R
import ru.axdar.chatmessenger.domain.friends.FriendEntity

class FriendsAdapter : BaseAdapter<FriendsAdapter.FriendViewHolder>() {

    class FriendViewHolder(view: View) : BaseViewHolder(view) {

        init {
            view.btnRemove.setOnClickListener {
                onClick?.onClick(item, it)
            }
        }

        override fun onBind(item: Any) {
            (item as? FriendEntity)?.let {
                view.tvName.text = it.name
                view.tvStatus.text = it.status
            }
        }

    }

    override val layoutRes: Int = R.layout.item_friend

    override fun createHolder(view: View, viewType: Int): FriendViewHolder {
        return FriendViewHolder(view)
    }
}