package ru.axdar.chatmessenger.ui.friends

import android.view.View
import kotlinx.android.synthetic.main.item_friend_request.view.*
import ru.axdar.chatmessenger.ui.core.BaseAdapter
import ru.axdar.chatmessenger.R
import ru.axdar.chatmessenger.domain.friends.FriendEntity

class FriendRequestsAdapter : BaseAdapter<FriendRequestsAdapter.FriendRequestsViewHolder>() {

    class FriendRequestsViewHolder(view: View) : BaseViewHolder(view) {

        init {
            view.btnApprove.setOnClickListener {
                onClick?.onClick(item, it)
            }
            view.btnCancel.setOnClickListener {
                onClick?.onClick(item, it)
            }
        }

        override fun onBind(item: Any) {
            (item as? FriendEntity)?.let {
                view.tvName.text = it.name
            }
        }

    }

    override val layoutRes: Int = R.layout.item_friend_request

    override fun createHolder(view: View, viewType: Int): FriendRequestsViewHolder {
        return FriendRequestsViewHolder(view)
    }
}