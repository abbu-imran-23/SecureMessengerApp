package com.example.securemessengerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var login : Button
    private lateinit var newuser : TextView

    //firebase auhentication
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //authentication initialization
        mAuth = FirebaseAuth.getInstance()

        email = findViewById(R.id.log_email)
        password = findViewById(R.id.log_pass)
        login = findViewById(R.id.log_btn)
        newuser = findViewById(R.id.new_user)

        newuser.setOnClickListener {
            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }

        //auth
        login.setOnClickListener{
            val email = email.text.toString()
            val password = password.text.toString()
            login(email,password)
        }

    }

    private fun login(email:String , password:String)
    {
        //logic for loging user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this,"Login successfull !",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,ChooseUser::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Invalid user or password",Toast.LENGTH_SHORT).show()
                }
            }
    }

}