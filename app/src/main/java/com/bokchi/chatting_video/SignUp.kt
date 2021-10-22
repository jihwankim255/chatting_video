package com.bokchi.chatting_video

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bokchi.chatting_video.Model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = Firebase.auth


        btnSignUp.setOnClickListener {
            auth.createUserWithEmailAndPassword(Email_SignUp.text.toString().trim(), Pass_SignUp.text.toString().trim())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(baseContext, "SignUp Done.",
                            Toast.LENGTH_LONG).show()

                        val uid = FirebaseAuth.getInstance().uid?:""

                        val user = User(uid)

                        // 여기에서 데이터베이스에 넣음
                        val db = Firebase.firestore.collection("users")
                        db.document(uid)
                            .set(user)
                            .addOnSuccessListener {
                                Log.d(TAG, "데이터베이스 성공")
                            }
                            .addOnFailureListener {
                                Log.d(TAG, "데이터베이스 실패")
                            }

                        val intent = Intent(this, ChatList::class.java)
                        startActivity(intent)

                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_LONG).show()

                    }
                }
        }


    }
}