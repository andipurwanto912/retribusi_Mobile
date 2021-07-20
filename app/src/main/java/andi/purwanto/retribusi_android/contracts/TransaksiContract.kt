package andi.purwanto.retribusi_android.contracts

interface TransaksiContract {
    interface TransaksiView{
        fun showToast(message : String)
        fun showLoading()
        fun hideLoading()
        fun success()
    }

    interface TransaksiPresenter{
        fun postTransaksi(token: String, rsapikey: String, bulan : String, nik : String, nama_lengkap : String, alamat : String, kelurahan : String,kecamatan : String,seri : String,jml_bayar : String)
        fun destroy()
    }
}