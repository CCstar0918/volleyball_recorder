package com.jack.recorder

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract


class LoginActivityContract: ActivityResultContract<String, Bundle>() {
    override fun createIntent(context: Context, input: String): Intent {
        return Intent(context, Login::class.java).apply {
            putExtra("LoginCall", input)

        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Bundle {
        return intent?.getBundleExtra("info")!!


    }
}

class MainActivity : AppCompatActivity() {
    private var hostname = "A"
    private var guestname = "B"
    private var game = "0"


    private val startForResult = registerForActivityResult(LoginActivityContract()) { result ->
        // get the result from Login activity: host name, guest name, game
        hostname = result.getString("host_name")!!
        guestname = result.getString("guest_name")!!
        game = result.getString("game")!!


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startForResult.launch("From Main to Login")





    }


}
