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
                b.putString("action", "ATTACK")
                DialogEvent(3)
            }
            R.id.btn_block -> {
                b.putString("action", "BLOCK")
                DialogEvent(4)
            }
            R.id.btn_lift -> {
                b.putString("action", "LIFT")
                DialogEvent(5)
            }
            R.id.btn_receive -> {
                b.putString("action", "RECEIVE")
                DialogEvent(2)
            }
            R.id.btn_receiveservice -> {
                b.putString("action", "RECEIVESERVICE")
                DialogEvent(2)
            }
            R.id.btn_service -> {
                b.putString("action", "SERVICE")
                DialogEvent(1)
            }
            R.id.btn_fault -> {
                b.putString("action", "FAULT")
                DialogEvent(6)
            }
        } // when



        bag_intent = Intent().apply {
            putExtra("record", b)
        }
        //setResult(Activity.RESULT_OK, bag_intent)
        //finish()
    } // onClick

    private fun DialogEvent(temp: Int) {
        // 1 -> 發球,  2 -> 接球,  3 -> 攻擊, 4 -> 攔網, 5 -> 舉球, 6 -> fault
        when(temp) {
            1 ->
                AlertDialog.Builder(this)
                    .setItems(serviceList.toTypedArray()) { _, which ->
                        Toast.makeText(this, serviceList[which], Toast.LENGTH_LONG).show()
                        setResult(Activity.RESULT_OK, bag_intent)
                        finish()
                    }
                    .show()
            2 ->
                AlertDialog.Builder(this)
                    .setItems(receiveList.toTypedArray()) { _, which ->
                        Toast.makeText(this, receiveList[which], Toast.LENGTH_LONG).show()
                        setResult(Activity.RESULT_OK, bag_intent)
                        finish()
                    }
                    .show()
            3 ->
                AlertDialog.Builder(this)
                    .setItems(attackList.toTypedArray()) { _, which ->
                        Toast.makeText(this, attackList[which], Toast.LENGTH_LONG).show()
                        setResult(Activity.RESULT_OK, bag_intent)
                        finish()
                    }
                    .show()
            4 ->
                AlertDialog.Builder(this)
                    .setItems(blockList.toTypedArray()) { _, which ->
                        Toast.makeText(this, blockList[which], Toast.LENGTH_LONG).show()
                        setResult(Activity.RESULT_OK, bag_intent)
                        finish()
                    }
                    .show()
            5 ->
                AlertDialog.Builder(this)
                    .setItems(liftList.toTypedArray()) { _, which ->
                        Toast.makeText(this, liftList[which], Toast.LENGTH_LONG).show()
                        setResult(Activity.RESULT_OK, bag_intent)
                        finish()
                    }
                    .show()
            6 ->
                AlertDialog.Builder(this)
                    .setItems(faultList.toTypedArray()) { _, which ->
                        Toast.makeText(this, faultList[which], Toast.LENGTH_LONG).show()
                        setResult(Activity.RESULT_OK, bag_intent)
                        finish()
                    }
                    .show()
        }

    } // DialogEvent()
}