package com.jack.recorder

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class NewPlayer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_player)
        val btn = findViewById<Button>(R.id.btn_send)
        val name = findViewById<EditText>(R.id.ed_name)
        val player_id = findViewById<EditText>(R.id.ed_id)

        btn.setOnClickListener {
            when{
                player_id.length() < 1 -> Toast.makeText(this, "請輸入 背號", Toast.LENGTH_LONG).show()
                name.length() < 1 -> Toast.makeText(this, "請輸入 名字", Toast.LENGTH_LONG).show()
                else -> {
                    val b = Bundle()
                    b.putInt("id", player_id.text.toString().toInt() )
                    b.putString("name", name.text.toString())
                    val bag_intent = Intent().apply {
                        putExtra("newplayer", b)
                    }
                    setResult(Activity.RESULT_OK, bag_intent)
                    finish()

                } // else
            } // when
        } // setOnClickListener
    }
}