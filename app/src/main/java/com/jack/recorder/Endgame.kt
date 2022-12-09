package com.jack.recorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate

class Endgame : AppCompatActivity() {
    private lateinit var ddadapter: DataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endgame)


        val text_team = findViewById<TextView>(R.id.team_textview)
        val text_game = findViewById<TextView>(R.id.game_textview)
        val text_point = findViewById<EditText>(R.id.point_view)
        val text_date = findViewById<TextView>(R.id.date_textview)
        val text_missing = findViewById<TextView>(R.id.missing_textview)
        val dRecyclerView = findViewById<RecyclerView>(R.id.dataView)

        val data = intent.getBundleExtra("data")
        //val datalist = data?.getSerializable("datalist")!!

        dRecyclerView.setHasFixedSize(true)
        dRecyclerView.layoutManager = LinearLayoutManager(this)

        ddadapter = DataAdapter(player_list)
        dRecyclerView.adapter = ddadapter

        val today_month = LocalDate.now().monthValue
        val today_day = LocalDate.now().dayOfMonth


        text_team.setText(data?.getString("hostname")!! + ":" + data?.getString("guestname")!!)
        text_game.setText("第" + data.getString("game") + "局")
        text_missing.setText("對方失誤: " + data.getString("miss") )
        text_date.setText(today_month.toString() + "月" + today_day.toString() + "日" )

    }
}