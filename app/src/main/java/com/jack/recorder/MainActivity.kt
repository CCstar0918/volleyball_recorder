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
import kotlin.io.path.createTempDirectory

data class Service(var times: Int, var point: Int, var error_time: Int )

data class ReceiveService(var A: Int, var B: Int, var C: Int, var error_time: Int)

data class Receive(var A: Int, var B: Int, var C: Int, var error_time: Int)

data class Attack(var Time: Int, var spike: Int, var spikepoint: Int, var drop: Int, var droppoint: Int, var error_time: Int)

data class Block(var touch: Int, var touchout: Int, var point: Int, var error_time: Int)

data class Lifting(var times: Int, var error_time: Int)

data class Fault( var twice: Int, var holding: Int, var touchnet: Int, var passingline: Int, var backattack: Int, var foot: Int, var other: Int)

data class Player(val num: Int, val name: String) {
    var service = Service(0, 0, 0)
    var receiveservice = ReceiveService(0,0,0,0)
    var receive = Receive(0, 0, 0, 0)
    var attack = Attack(0, 0, 0, 0, 0, 0)
    var block = Block(0, 0, 0, 0)
    var lifting = Lifting(0, 0)
    var fault = Fault(0, 0, 0, 0, 0, 0, 0)

} // Player()

// ********** Contract **********

class RecordContract: ActivityResultContract<Int, Bundle>() { // RecordContract
    override fun createIntent(context: Context, input: Int): Intent {
        return Intent(context, Action::class.java).apply {
            putExtra("position", input)
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
var player_list = ArrayList<Player>()
class MainActivity : AppCompatActivity(), Myadapter.OnItemClickListener {
    private var hostname = "A"
    private var guestname = "B"
    private var game = "0"

    private var miss: Int = 0

    private lateinit var adapter: Myadapter


    // ********** Register Contract **********
    private val recordForResult = registerForActivityResult(RecordContract()) { result ->
        // recording the player data

        if(result.getString("action") != "0") {
            when( result.getString("action") ) {
                "攻擊" -> {
                    when( result.getString("level") ) {
                        "次數" -> player_list[result.getInt("position")].attack.Time++
                        "扣球" -> player_list[result.getInt("position")].attack.spike++
                        "扣球得分" -> player_list[result.getInt("position")].attack.spikepoint++
                        "吊球" -> player_list[result.getInt("position")].attack.drop++
                        "吊球得分" -> player_list[result.getInt("position")].attack.droppoint++
                        "失誤" -> player_list[result.getInt("position")].attack.error_time++
                    } // when
                } // "攻擊"
                "攔網" -> {
                    when( result.getString("level") ) {
                        "觸球" -> player_list[result.getInt("position")].block.touch++
                        "Touch Out" -> player_list[result.getInt("position")].block.touchout++
                        "得分" -> player_list[result.getInt("position")].block.point++
                        "失誤" -> player_list[result.getInt("position")].block.error_time++
                    } // when
                } // "攔網"
                "舉球" -> {
                    when( result.getString("level") ) {
                        "次數" -> player_list[result.getInt("position")].lifting.times++
                        "失誤" -> player_list[result.getInt("position")].lifting.error_time++
                    } // when
                } // "舉球"
                "接球" -> {
                    when( result.getString("level") ) {
                        "A" -> player_list[result.getInt("position")].receive.A++
                        "B" -> player_list[result.getInt("position")].receive.B++
                        "C" -> player_list[result.getInt("position")].receive.C++
                        "失誤" -> player_list[result.getInt("position")].receive.error_time++
                    } // when
                } // "接球"
                "接發" -> {
                    when( result.getString("level") ) {
                        "A" -> player_list[result.getInt("position")].receiveservice.A++
                        "B" -> player_list[result.getInt("position")].receiveservice.B++
                        "C" -> player_list[result.getInt("position")].receiveservice.C++
                        "失誤" -> player_list[result.getInt("position")].receiveservice.error_time++
                    } // when
                } // "接發"
                "發球" -> {
                    when( result.getString("level") ) {
                        "次數" -> player_list[result.getInt("position")].service.times++
                        "得分" -> player_list[result.getInt("position")].service.point++
                        "失誤" -> player_list[result.getInt("position")].service.error_time++
                    } // when
                } // "發球"
                "犯規" -> {
                    when( result.getString("level") ) {
                        "2次" -> player_list[result.getInt("position")].fault.twice++
                        "持球" -> player_list[result.getInt("position")].fault.holding++
                        "觸網" -> player_list[result.getInt("position")].fault.touchnet++
                        "越界" -> player_list[result.getInt("position")].fault.passingline++
                        "後排擊球" -> player_list[result.getInt("position")].fault.backattack++
                        "發球踩線" -> player_list[result.getInt("position")].fault.foot++
                        "其他" -> player_list[result.getInt("position")].fault.other++
                    } // when
                } // "犯規"
            }
            val str : String = player_list[result.getInt("position")].num.toString() + " " + player_list[result.getInt("position")].name + " "  + result.getString("action") + " " + result.getString("level")
            Toast.makeText(this,str, Toast.LENGTH_LONG).show()
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
        val btn_miss = findViewById<Button>(R.id.miss_btn)
        val btn_statistice = findViewById<Button>(R.id.statistice_btn)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = Myadapter(player_list, this)
        recyclerView.adapter = adapter



        // btn setOnClickListener
        btn_miss.setOnClickListener {
            miss++
            btn_miss.setText("對方失誤 " + miss.toString())
        }

        // setting new player button
        btn_add.setOnClickListener {
            newplayerForResult.launch(Unit)
        }

        btn_statistice.setOnClickListener{
            val alldata = Bundle()
            alldata.putString("hostname", hostname)
            alldata.putString("guestname", guestname)
            alldata.putString("game", game)
            alldata.putString("miss", miss.toString())
            //alldata.putSerializable("datalist", player_list)
            Intent( this, Endgame::class.java).apply {
                putExtra("data",alldata)
                startActivity(this)
            }
        }

        // btn setOnClickListener

        startForResult.launch("From Main to Login")
    }

    override fun onItemClick(position: Int) {
        // Log.d("who", player_list[position].name)
        recordForResult.launch(position)
    }
}
