package com.bokchi.chatting_video.Adapter

import com.bokchi.chatting_video.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.message_list_row.view.*

class UserItem(val name:String,val uid:String) : Item<GroupieViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.message_list_row
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.name.text = name

    }



}