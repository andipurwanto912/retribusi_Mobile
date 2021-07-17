package andi.purwanto.retribusi_android.contracts

import android.content.Context

interface LoginActivityContract {

    interface LoginView {
        fun showToast(message : String)
        fun successLogin()
        fun showLoading()
        fun hideLoading()
    }

    interface LoginPresenter{
        fun login(token : String, rsapikey : String, email : String, password : String, context: Context)
        fun destroy()
    }
}