package com.jack.recorder

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

class Action : AppCompatActivity(), View.OnClickListener {
    private val b = Bundle()
    private var bag_intent = Intent()
    private val serviceList : List<String> = listOf("次數", "得分", "失誤" ) // 1
    private val receiveList : List<String> = listOf("A", "B", "C", "失誤") // 2
    private val attackList : List<String> = listOf("次數", "扣球", "扣球得分", "吊球", "吊球得分", "失誤") // 3
    private val blockList : List<String> = listOf("觸球", "Touch Out", "得分", "失誤") // 4
    private val liftList : List<String> = listOf("次數", "失誤") // 5
    private val faultList : List<String> = listOf("2次", "持球", "觸網", "越界", "後排擊球", "發球踩線", "其他") // 6
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action)
        val position : Int = intent.getIntExtra("position",-1)
        b.putInt("position", position)

        val attack = findViewById<Button>(R.id.btn_attack)
        val blocker = findViewById<Button>(R.id.btn_block)
        val liftinger = findViewById<Button>(R.id.btn_lift)
        val receiver = findViewById<Button>(R.id.btn_receive)
        val receiveserviceer = findViewById<Button>(R.id.btn_receiveservice)
        val serviceer = findViewById<Button>(R.id.btn_service)
        val faulter = findViewById<Button>(R.id.btn_fault)

        attack.setOnClickListener(this)
        blocker.setOnClickListener(this)
        liftinger.setOnClickListener(this)
        receiver.setOnClickListener(this)
        receiveserviceer.setOnClickListener(this)
        serviceer.setOnClickListener(this)
        faulter.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_attack -> {
                b.putString("action", "攻擊")
                DialogEvent(attackList)
            }
            R.id.btn_block -> {
                b.putString("action", "攔網")
                DialogEvent(blockList)
            }
            R.id.btn_lift -> {
                b.putString("action", "舉球")
                DialogEvent(liftList)
            }
            R.id.btn_receive -> {
                b.putString("action", "接球")
                DialogEvent(receiveList)
            }
            R.id.btn_receiveservice -> {
                b.putString("action", "接發")
                DialogEvent(receiveList)
            }
            R.id.btn_service -> {
                b.putString("action", "發球")
                DialogEvent(serviceList)
            }
            R.id.btn_fault -> {
                b.putString("action", "犯規")
                DialogEvent(faultList)
            }
        } // when

        bag_intent = Intent().apply {
            putExtra("record", b)
        }
    } // onClick

    private fun DialogEvent(temp: List<String>) {
        AlertDialog.Builder(this)
            .setItems(temp.toTypedArray()) { _, which ->
                //Toast.makeText(this, serviceList[which], Toast.LENGTH_LONG).show()
                b.putString("level", temp[which])
                setResult(Activity.RESULT_OK, bag_intent)
                finish()
            }
            .show()


    } // DialogEvent()
}