package andi.purwanto.retribusi_android.adapters

import andi.purwanto.retribusi_android.R
import andi.purwanto.retribusi_android.models.Seri
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_seri.view.*

class SeriActivityAdapter(private var seri : List<Seri>, private var context : Context) : RecyclerView.Adapter<SeriActivityAdapter.MyHolder>() {
    inner class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(seri: Seri, context: Context){
            itemView.tvSeriA.text = seri.seri
            itemView.tvTagihan.text = seri.tagihan
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.list_item_seri, parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) = holder.bind(seri[position], context)

    override fun getItemCount() = seri.size
}