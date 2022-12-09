package com.jack.recorder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class  DataAdapter(private val data: ArrayList<Player>) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val numd = v.findViewById<TextView>(R.id.num_t)
        val named = v.findViewById<TextView>(R.id.name_t)
        val serd = v.findViewById<TextView>(R.id.ser_t)
        val rsd = v.findViewById<TextView>(R.id.rs_t)
        val rd = v.findViewById<TextView>(R.id.r_t)
        val attd = v.findViewById<TextView>(R.id.att_t)
        val bd = v.findViewById<TextView>(R.id.b_t)
        val ld = v.findViewById<TextView>(R.id.l_t)
        val fd = v.findViewById<TextView>(R.id.f_t)

    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): DataAdapter.ViewHolder {
        // 建立 ViewHolder 與 Layout 之間的連接
        // 將 adapter.xml 的 資料型態呈現在 ViewHolder
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.data_adapter, viewGroup, false)
        return ViewHolder(v)
    } // onCreateViewHolder()

    override fun onBindViewHolder(holder: DataAdapter.ViewHolder, position: Int) {
        holder.numd.text = data[position].num.toString()
        holder.named.text = data[position].name
        // service START
        var temp = "[發球] "
        if ( data[position].service.times != 0 )
            temp = temp + " 次數" + data[position].service.times.toString()
        if ( data[position].service.point != 0 )
            temp = temp + " 得分" + data[position].service.point.toString()
        if ( data[position].service.error_time != 0 )
            temp = temp + " 次數" + data[position].service.error_time.toString()
        holder.serd.text = temp
        // service END
        // receiveservice START
        temp = "[接發] "
        if ( data[position].receiveservice.A != 0 )
            temp = temp + " A" + data[position].receiveservice.A.toString()
        if ( data[position].receiveservice.B != 0 )
            temp = temp + " B" + data[position].receiveservice.B.toString()
        if ( data[position].receiveservice.C != 0 )
            temp = temp + " C" + data[position].receiveservice.C.toString()
        if ( data[position].receiveservice.error_time != 0 )
            temp = temp + " 失誤" + data[position].receiveservice.error_time.toString()
        holder.rsd.text = temp
        // receiveservice END
        // receive START
        temp = "[接球] "
        if ( data[position].receive.A != 0 )
            temp = temp + " A" + data[position].receive.A.toString()
        if ( data[position].receive.B != 0 )
            temp = temp + " B" + data[position].receive.B.toString()
        if ( data[position].receive.C != 0 )
            temp = temp + " C" + data[position].receive.C.toString()
        if ( data[position].receive.error_time != 0 )
            temp = temp + " 失誤" + data[position].receive.error_time.toString()
        holder.rd.text = temp
        // receive END
        // attack START
        temp = "[攻擊] "
        if ( data[position].attack.Time != 0 )
            temp = temp + " 次數" + data[position].attack.Time.toString()
        if ( data[position].attack.spike != 0 )
            temp = temp + " 扣球" + data[position].attack.spike.toString()
        if ( data[position].attack.spikepoint != 0 )
            temp = temp + " 扣球得分" + data[position].attack.spikepoint.toString()
        if ( data[position].attack.drop != 0 )
            temp = temp + " 吊球" + data[position].attack.drop.toString()
        if ( data[position].attack.droppoint != 0 )
            temp = temp + " 吊球得分" + data[position].attack.droppoint.toString()
        if ( data[position].attack.error_time != 0 )
            temp = temp + " 失誤" + data[position].attack.error_time.toString()
        holder.attd.text = temp
        // attack END
        // block START
        temp = "[攔網] "
        if ( data[position].block.touch != 0)
            temp = temp + " 觸球" + data[position].block.touch.toString()
        if ( data[position].block.touchout != 0)
            temp = temp + " TouchOut" + data[position].block.touchout.toString()
        if ( data[position].block.point != 0)
            temp = temp + " 得分" + data[position].block.point.toString()
        if ( data[position].block.error_time != 0)
            temp = temp + " 失誤" + data[position].block.error_time.toString()
        holder.bd.text = temp
        // block END
        // lifting START
        temp = "[舉球] "
        if ( data[position].lifting.times != 0)
            temp = temp + " 次數" + data[position].lifting.times.toString()
        if ( data[position].lifting.error_time != 0)
            temp = temp + " 次數" + data[position].lifting.error_time.toString()
        holder.ld.text = temp
        // lifting END
        // fault START
        temp = "[犯規] "
        if ( data[position].fault.twice != 0)
            temp = temp + " 2次" + data[position].fault.twice.toString()
        if ( data[position].fault.holding != 0)
            temp = temp + " 持球" + data[position].fault.holding.toString()
        if ( data[position].fault.touchnet != 0)
            temp = temp + " 觸網" + data[position].fault.touchnet.toString()
        if ( data[position].fault.passingline != 0)
            temp = temp + " 越界" + data[position].fault.passingline.toString()
        if ( data[position].fault.backattack != 0)
            temp = temp + " 後排擊球" + data[position].fault.backattack.toString()
        if ( data[position].fault.foot != 0)
            temp = temp + " 發球採現" + data[position].fault.foot.toString()
        if ( data[position].fault.other != 0)
            temp = temp + " 其他" + data[position].fault.other.toString()
        holder.fd.text = temp
        // fault END

    } // onBindViewHolder()
}