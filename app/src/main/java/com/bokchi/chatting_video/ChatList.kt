package com.bokchi.chatting_video

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_chat_list.*

class ChatList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)

        val adapter = GroupAdapter
        //https://show-me-the-money.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-Groupie%EB%A1%9C-%ED%99%95%EC%9E%A5-%EA%B0%80%EB%8A%A5%ED%95%9C-RecyclerView-%EB%A7%8C%EB%93%A4%EA%B8%B0
        recyclerview_list


    }
}