package com.jack.recorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)








        findViewById<Button>(R.id.game_button).setOnClickListener {

            val host_name = findViewById<EditText>(R.id.host).text.toString()
            val guest_name = findViewById<EditText>(R.id.guest).text.toString()
            val game = findViewById<EditText>(R.id.game).text.toString()
            Log.d("host_name", host_name)
            Log.d("guest_name", guest_name)
            Log.d("game", game)


            finish()
        }
    }

}