package andi.purwanto.retribusi_android.contracts

import andi.purwanto.retribusi_android.models.Masyarakat

interface TransaksiContract {
    interface TransaksiView{
        fun showToast(message : String)
        fun showLoading()
        fun hideLoading()
        fun fill(masyarakat : List<Masyarakat>)
        fun success()
    }

    interface TransaksiPresenter{
        fun postTransaksi(token: String, rsapikey: String, bulan : String, nik : String, nama_lengkap : String, alamat : String, kelurahan : String,kecamatan : String,seri : String,jml_bayar : String)
        fun getMasyarakatByNik(token: String, rsapikey: String, nik : String)
        fun destroy()
    }
}