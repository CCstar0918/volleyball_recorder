package com.jack.recorder

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Space
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

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

// ********** Contract **********

class RecordContract: ActivityResultContract<Unit, Bundle>() { // RecordContract
    override fun createIntent(context: Context, input: Unit): Intent {
        return Intent(context, Action::class.java).apply {
            // 不放 anything
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Bundle {

        val b = Bundle()
        b.putString("action", "0")
        return intent?.getBundleExtra("record") ?: b

        // 若使用 back 鍵，會沒有 return value
    }

}

class NewPlayerContract: ActivityResultContract<Unit, Bundle>() { // NewPlayerContract
    override fun createIntent(context: Context, input: Unit): Intent {
        return Intent(context, NewPlayer::class.java).apply {
            // 不放 anything
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Bundle {
        val b = Bundle()
        b.putInt("id", 0)
        return intent?.getBundleExtra("newplayer") ?: b
        // 若使用 back 鍵，會沒有 return value
    }

}

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

// ********** Contract **********

class MainActivity : AppCompatActivity(), Myadapter.OnItemClickListener {
    private var hostname = "A"
    private var guestname = "B"
    private var game = "0"

    private lateinit var adapter: Myadapter
    private var player_list = ArrayList<Player>()

    // ********** Register Contract **********
    private val recordForResult = registerForActivityResult(RecordContract()) { result ->
        // get the result from NewPlayer activity: id, name

        if(result.getString("action") != "0") {
            Toast.makeText(this,result.getString("action"), Toast.LENGTH_LONG).show()
        }
    }

    private val newplayerForResult = registerForActivityResult(NewPlayerContract()) { result ->
        // get the result from NewPlayer activity: id, name

        if(result.getInt("id") != 0) {
            player_list.add(Player(result.getInt("id")!!, result.getString("name")!!))
            adapter.notifyDataSetChanged()
        }
    }

    private val startForResult = registerForActivityResult(LoginActivityContract()) { result ->
        // get the result from Login activity: host name, guest name, game
        hostname = result.getString("host_name")!!
        guestname = result.getString("guest_name")!!
        game = result.getString("game")!!
    }

    // ********** Register Contract **********

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.player_recyclerView)
        recyclerView.setHasFixedSize(true)
        val btn_add = findViewById<Button>(R.id.add_btn)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = Myadapter(player_list, this)
        recyclerView.adapter = adapter



        // setting new player button
        btn_add.setOnClickListener {
            newplayerForResult.launch(Unit)
        }
        startForResult.launch("From Main to Login")
    }

    override fun onItemClick(position: Int) {
        recordForResult.launch(Unit)
    }
}
