package com.jack.recorder

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts



class LoginActivityContract: ActivityResultContract<Void?, Bundle>() {
    override fun createIntent(context: Context, input: Void?): Intent {
        return Intent(context, login::class.java).apply {

        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Bundle {
        TODO("Not yet implemented")
    }
}

class MainActivity : AppCompatActivity() {
    /*
    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            // Handle the Intent
        }
    }


     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Intent(  this, login::class.java ).apply {
            startActivity(this)
        }

        // 切換到 login 介面




    }


}
