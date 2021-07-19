package andi.purwanto.retribusi_android.contracts

import andi.purwanto.retribusi_android.models.Riwayat

interface RiwayatActivityContract {
    interface RiwayatView {
        fun attachToRecycler(riwayat: List<Riwayat>)
        fun showToast(message : String)
        fun showLoading()
        fun hideLoading()
    }

    interface RiwayatPresenter {
        fun getRiwayat(token : String, rsapikey : String)
        fun destroy()
    }
}