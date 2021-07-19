package andi.purwanto.retribusi_android.adapters

import andi.purwanto.retribusi_android.R
import andi.purwanto.retribusi_android.models.Riwayat
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_riwayat.view.*

class RiwayatActivityAdapater(private var riwayat : List<Riwayat>, private var context : Context) : RecyclerView.Adapter<RiwayatActivityAdapater.MyHolder>() {
    inner class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(riwayat: Riwayat, context: Context){
            itemView.tvBulan.text = riwayat.bulan
            itemView.tvNamaP.text = riwayat.nama_lengkap
            itemView.tvSeriP.text = riwayat.seri
            itemView.tvJmlbayar.text = riwayat.jml_bayar
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.list_item_riwayat, parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) = holder.bind(riwayat[position], context)

    override fun getItemCount() = riwayat.size
}