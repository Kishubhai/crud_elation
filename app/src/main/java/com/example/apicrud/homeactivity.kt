package com.example.apicrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

class homeactivity : AppCompatActivity() {
    private lateinit var logout:ImageView
    private lateinit var delete:TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homeactivity)

        logout = findViewById(R.id.btn_logout)
        auth = FirebaseAuth.getInstance()
        delete = findViewById(R.id.tv_delete)

        logout.setOnClickListener {
            auth.signOut()
            val intent  = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        delete.setOnClickListener {
            deleteUser()
        }
    }
    private fun deleteUser() {
        val user = auth.currentUser

        user?.delete()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(baseContext, "Account deleted successfully.", Toast.LENGTH_SHORT).show()
                // Update UI to reflect the account deletion, if necessary
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(baseContext, "Account deletion failed.", Toast.LENGTH_SHORT).show()
                // If deletion fails, the user might need to re-authenticate

            }
        }
    }

}