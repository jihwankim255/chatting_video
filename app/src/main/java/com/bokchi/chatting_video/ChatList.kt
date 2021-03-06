package com.bokchi.chatting_video

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bokchi.chatting_video.Adapter.UserItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_chat_list.*

class ChatList : AppCompatActivity() {

    private val TAG = ChatList::class.java.simpleName
    val db = Firebase.firestore

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)
        auth = Firebase.auth

        myChatList.setOnClickListener {
            val intent = Intent(this, MyRoomActivity::class.java)
            startActivity(intent)
        }

        val adapter = GroupAdapter<GroupieViewHolder>()

        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    adapter.add(UserItem(document.get("username").toString(), document.get("uid").toString()))
                    Log.d(TAG, document.get("username").toString())
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                recyclerview_list.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        adapter.setOnItemClickListener { item, view ->

            val uid = (item as UserItem).uid
            val name = (item as UserItem).name

            val intent = Intent(this, ChatROomActivity::class.java)
            intent.putExtra("yourUid", uid)
            intent.putExtra("name", name)
            startActivity(intent)
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item?.itemId
        if (id == R.id.itemLogOut) {
            Firebase.auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        return true
    }
}