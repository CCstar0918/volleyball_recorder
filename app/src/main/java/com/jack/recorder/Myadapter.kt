package com.jack.recorder


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView




class Myadapter(private val data: ArrayList<Player>, private val listener: OnItemClickListener) : RecyclerView.Adapter<Myadapter.ViewHolder>() {

    // 實作 RecyclerView.ViewHolder 來儲存 view
    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener { // class ViewHolder()
        // 連結畫面中的元件

        val player_btn = v.findViewById<TextView>(R.id.player_btn)

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            listener.onItemClick(position)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // 建立 ViewHolder 與 Layout 之間的連接
        // 將 adapter.xml 的 資料型態呈現在 ViewHolder
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter, viewGroup, false)
        return ViewHolder(v)
    } // onCreateViewHolder()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 將資料指派給元件呈現
        val num = data[position].num.toString() + " "
        val name = data[position].name
        //Log.d("name", name)
        holder.player_btn.text = num + name
    } // onBindViewHolder()





}
