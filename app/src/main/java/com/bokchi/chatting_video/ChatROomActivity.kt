package com.bokchi.chatting_video

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bokchi.chatting_video.Model.ChatLeftYou
import com.bokchi.chatting_video.Model.ChatRightMe
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_chat_room.*

class ChatROomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        val adapter = GroupAdapter<GroupieViewHolder>()

        adapter.add(ChatLeftYou())
        adapter.add(ChatLeftYou())
        adapter.add(ChatRightMe())
        adapter.add(ChatLeftYou())
        adapter.add(ChatRightMe())
        adapter.add(ChatRightMe())
        adapter.add(ChatLeftYou())

        recyclerView_chat.adapter = adapter

    }
}