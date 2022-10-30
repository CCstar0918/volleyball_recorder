package com.jack.recorder


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class Myadapter(private val data: ArrayList<Player>) :
    RecyclerView.Adapter<Myadapter.ViewHolder>() {
    // 實作 RecyclerView.ViewHolder 來儲存 view
    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        // 連結畫面中的元件
        val player_btn = v.findViewById<Button>(R.id.player_btn)
    } // class ViewHolder()

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // 建立 ViewHolder 與 Layout 之間的連接
        // 將 adapter.xml 的 資料型態呈現在 ViewHolder
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter, viewGroup, false)
        return ViewHolder(v)
    } // onCreateViewHolder()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 將資料指派給元件呈現
        holder.player_btn.text = "player1 測試"
        holder.player_btn.textSize = 30F

    } // onBindViewHolder()
}
