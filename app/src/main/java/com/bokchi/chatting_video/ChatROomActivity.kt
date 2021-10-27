package com.bokchi.chatting_video

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bokchi.chatting_video.Adapter.ChatLeftYou
import com.bokchi.chatting_video.Adapter.ChatRightMe
import com.bokchi.chatting_video.Model.ChatModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_chat_room.*

class ChatROomActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;
    private val TAG = ChatROomActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        auth = FirebaseAuth.getInstance()

        val myUid = auth.uid
        val yourUid = intent.getStringExtra("yourUid")
        val name = intent.getStringExtra("name")

        val adapter = GroupAdapter<GroupieViewHolder>()

//        adapter.add(ChatLeftYou())
//        adapter.add(ChatLeftYou())
//        adapter.add(ChatRightMe())
//        adapter.add(ChatLeftYou())
//        adapter.add(ChatRightMe())
//        adapter.add(ChatRightMe())
//        adapter.add(ChatLeftYou())

        // 데이터 불러오기



        val db =  FirebaseFirestore.getInstance()

        db.collection("message")
            .orderBy("time")
            .get()
            .addOnSuccessListener { result->
                for (document in result) {
                    Log.d(TAG, document.toString())

                    val senderUid = document.get("myUid")
                    val msg = document.get("message").toString()

                    if (senderUid == myUid){
                        adapter.add(ChatRightMe(msg))
                    } else {
                        adapter.add(ChatLeftYou(msg))
                    }
                    //만약 내가 보낸 메세지일 때
                    //만약 내가 보낸 메세지가 아닐 때
                }

                recyclerView_chat.adapter = adapter
            }

        val database = Firebase.database
        val myRef = database.getReference("message")

        button.setOnClickListener {
            val message  = editText.text.toString()

            val chat = ChatModel(myUid.toString(), yourUid, message, System.currentTimeMillis())
            myRef.child(myUid.toString()).child(yourUid).push().setValue(chat)

            val chat_get = ChatModel(yourUid, myUid.toString(), message, System.currentTimeMillis())
            myRef.child(yourUid).child(myUid.toString()).push().setValue(chat_get)

            editText.setText("")
//
//
//
//
//
//
//            db.collection("message")
//                .add(chat)
//                .addOnSuccessListener {
//                    Log.d(TAG, "성공")
//                }
//                .addOnFailureListener {
//                    Log.d(TAG, "실패")
//                }

        }
    }
}