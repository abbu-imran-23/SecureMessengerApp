package com.example.securemessengerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class Signup : AppCompatActivity() {

    private lateinit var name : EditText
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var signupbtn : Button
    private lateinit var alreadyuser : TextView

    //firebase auhentication
    private lateinit var mAuth : FirebaseAuth
    //firebase database
    private lateinit var mDbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //authentication initialization
        mAuth = FirebaseAuth.getInstance()

        name = findViewById(R.id.signup_name)
        email = findViewById(R.id.signup_email)
        password = findViewById(R.id.signup_pass)
        signupbtn = findViewById(R.id.signup_btn)
        alreadyuser = findViewById(R.id.already_user)

        alreadyuser.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }

        signupbtn.setOnClickListener{
            val email = email.text.toString()
            val name = name.text.toString()
            val password = password.text.toString()
            signup(name,email,password)
        }
    }

    private fun signup(name : String, email:String, password:String)
    {
        //logic of creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    //add user to database
                    addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this,Login::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Some error occured",Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(name: String, email : String, uid:String){
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))
    }

}