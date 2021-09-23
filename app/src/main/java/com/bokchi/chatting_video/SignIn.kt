package com.bokchi.chatting_video

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignIn : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        auth = Firebase.auth


        btnSignIn.setOnClickListener {
            auth.signInWithEmailAndPassword(addEmail.text.toString().trim(), addPass.text.toString().trim())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(baseContext, "SignIn Done.",
                            Toast.LENGTH_LONG).show()

                        val intent = Intent(this, Home::class.java)
                        startActivity(intent)

                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(baseContext, "SignIn failed.",
                            Toast.LENGTH_LONG).show()

                    }
                }
        }

    }
}