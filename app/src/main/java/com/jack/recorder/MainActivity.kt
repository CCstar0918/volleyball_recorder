package com.jack.recorder

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Service(var times: Int, var point: Int, var error_time: Int )

data class ReceiveService(var A: Int, var B: Int, var C: Int, var error_time: Int)

data class Receive(var A: Int, var B: Int, var C: Int, var error_time: Int)

data class Attack(var Time: Int, var spike: Int, var spikepoint: Int, var drop: Int, var droppoint: Int, var error_time: Int)

data class Block(var touch: Int, var touchout: Int, var point: Int, var error_time: Int)

data class Lifting(var times: Int, var error_time: Int)

data class Fault( var twice: Int, var holding: Int, var touchnet: Int, var passingline: Int)

data class Player(val num: Int, val name: String) {
    var service = Service(0, 0, 0)
    var receiveservice = ReceiveService(0,0,0,0)
    var receive = Receive(0, 0, 0, 0)
    var attack = Attack(0, 0, 0, 0, 0, 0)
    var block = Block(0, 0, 0, 0)
    var lifting = Lifting(0, 0)
    var fault = Fault(0, 0, 0, 0)

} // Player()

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

    private lateinit var adapter: Myadapter
    private var player_list = ArrayList<Player>()


    private val startForResult = registerForActivityResult(LoginActivityContract()) { result ->
        // get the result from Login activity: host name, guest name, game
        hostname = result.getString("host_name")!!
        guestname = result.getString("guest_name")!!
        game = result.getString("game")!!

        Log.d("123","imes.toString()")

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.player_recyclerView)

        val l = LinearLayoutManager(this)
        recyclerView.layoutManager = l

        adapter = Myadapter(player_list)
        recyclerView.adapter = adapter


        for ( i in 1 .. 20 ) {
            player_list.add(Player(1, "球員一號"))

        } // for
        adapter.notifyDataSetChanged()



        // var player = Player(6, "Jack")
        // player.service.times++

        startForResult.launch("From Main to Login")
    }





}
