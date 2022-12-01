package com.jack.recorder

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginCall = intent.getStringExtra("LoginCall")?: "error"
        Log.d("getMain", loginCall)

        val host_name = findViewById<EditText>(R.id.host)
        val guest_name = findViewById<EditText>(R.id.guest)
        val game = findViewById<EditText>(R.id.game)

        findViewById<Button>(R.id.game_button).setOnClickListener {



            if ( guest_name.text.toString() == "" || game.text.toString() == "" ) {
                if ( guest_name.text.toString() == "" ) {
                    Toast.makeText(this, "請輸入 guest name", Toast.LENGTH_LONG).show()
                } // if
                if ( game.text.toString() == "" ) {
                    Toast.makeText(this, "請輸入 game", Toast.LENGTH_LONG).show()
                } // if
            } // if

            // guest_name and game must not be NULL
            else {
                val bag = Bundle()
                bag.putString("host_name", host_name.text.toString())
                bag.putString("guest_name", guest_name.text.toString())
                bag.putString("game", game.text.toString())
                val bag_intent = Intent().apply {
                    putExtra("info", bag)
                }

                setResult(Activity.RESULT_OK, bag_intent)
                finish()
            } // else

        } // setOnClickListener
    } // onCreate

}