package com.bokchi.chatting_video.Model

import com.bokchi.chatting_video.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.message_list_row.view.*

class UserItem(val uid:String) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.name.text = uid

    }

    override fun getLayout(): Int {
        return R.layout.message_list_row
    }

}