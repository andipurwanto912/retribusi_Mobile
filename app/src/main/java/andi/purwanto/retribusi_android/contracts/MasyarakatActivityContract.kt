package andi.purwanto.retribusi_android.contracts

import andi.purwanto.retribusi_android.models.Masyarakat

interface MasyarakatActivityContract {

    interface MasyarakatActivityContract {
        interface MasyarakatView {
            fun attachToRecycler(masyarakat : List<Masyarakat>)
            fun showToast(message : String)
            fun showLoading()
            fun hideLoading()
        }

        interface MasyarakatPresenter {
            fun getMasyarakat(token : String, rsapikey : String)
            fun destroy()
        }
    }

    interface MasyarakatView {

    }
}