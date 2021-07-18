package andi.purwanto.retribusi_android.adapters

import andi.purwanto.retribusi_android.R
import andi.purwanto.retribusi_android.models.Masyarakat
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_masyarakat.view.*


class MasyarakatActivityAdapter(private var masyarakat : List<Masyarakat>, private var context : Context) : RecyclerView.Adapter<MasyarakatActivityAdapter.MyHolder>() {
    inner class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(masyarakat: Masyarakat, context: Context){
            itemView.tvSeri.text = masyarakat.seri
            itemView.tvNama.text = masyarakat.nama_lengkap
            itemView.tvNIK.text = masyarakat.nik
            itemView.tvAlamat.text = masyarakat.kecamatan
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.list_item_masyarakat, parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) = holder.bind(masyarakat[position], context)

    override fun getItemCount() = masyarakat.size
}