package andi.purwanto.retribusi_android.presenters

import android.content.Context
import android.util.Log
import andi.purwanto.retribusi_android.contracts.LoginActivityContract
import andi.purwanto.retribusi_android.models.User
import andi.purwanto.retribusi_android.responses.WrappedResponse
import andi.purwanto.retribusi_android.utilities.APIClient
import andi.purwanto.retribusi_android.utilities.Constants
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivityPresenter(v : LoginActivityContract.LoginView) : LoginActivityContract.LoginPresenter {
    private var view : LoginActivityContract.LoginView? = v
    private var apiService = APIClient.APIService()

    override fun login(token : String, rsapikey: String, email: String, password: String, context: Context) {
        val request = apiService.login(token, rsapikey,email, password)
        view?.showLoading()
        request.enqueue(object : Callback<WrappedResponse<User>> {
            override fun onFailure(call: Call<WrappedResponse<User>>, t: Throwable) {
                view?.showToast("Cannot connect to server")
                println(t.message)
                view?.hideLoading()
            }

            override fun onResponse(call: Call<WrappedResponse<User>>, response: Response<WrappedResponse<User>>) {
                println("RESPONSE LOGIN " + response)
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null && body.status.equals("1")){
                        var gson = Gson()
                        var json : String = gson.toJson(body.data)
                        Constants.setList(context, json)
                        Constants.setToken(context, body.data.api_token!!)
                        view?.showToast("Welcome back ${body.data.nama_lengkap}")
                        view?.successLogin()
                    }else{
                        view?.showToast("Failed to login. Check your email and password")
                    }
                    view?.hideLoading()
                }else{
                    view?.hideLoading()
                    view?.showToast("Email dan Password Tidak Terdaftar")
                }
            }
        })
    }

    override fun destroy() { view = null }
}