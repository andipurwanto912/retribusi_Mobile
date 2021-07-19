package andi.purwanto.retribusi_android.contracts

import andi.purwanto.retribusi_android.models.Seri

interface SeriActivityContract {
    interface SeriView {
        fun attachToRecycler(seri : List<Seri>)
        fun showToast(message : String)
        fun showLoading()
        fun hideLoading()
    }

    interface SeriPresenter {
        fun getSeri(token : String, rsapikey : String)
        fun destroy()
    }
}