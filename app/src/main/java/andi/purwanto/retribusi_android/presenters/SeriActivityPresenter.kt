package andi.purwanto.retribusi_android.presenters

import andi.purwanto.retribusi_android.contracts.SeriActivityContract
import andi.purwanto.retribusi_android.models.Seri
import andi.purwanto.retribusi_android.responses.WrappedListResponse
import andi.purwanto.retribusi_android.utilities.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeriActivityPresenter(v : SeriActivityContract.SeriView?) : SeriActivityContract.SeriPresenter {

    private var view : SeriActivityContract.SeriView? = v

    private var apiService = APIClient.APIService()

    override fun getSeri(token: String, rsapikey: String) {
        val request = apiService.getSeri(token, rsapikey)
        view?.showLoading()
        request.enqueue(object : Callback<WrappedListResponse<Seri>> {
            override fun onResponse(
                call: Call<WrappedListResponse<Seri>>,
                response: Response<WrappedListResponse<Seri>>
            ) {
                println("RESPONSE " + response)
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null){
                        view?.attachToRecycler(body.data)
                        view?.hideLoading()
                    }else{
                        view?.showToast("Terjadi kesalahan")
                        view?.hideLoading()
                    }
                }else{
                    view?.showToast("Body Kosong")
                    view?.hideLoading()
                }
            }

            override fun onFailure(call: Call<WrappedListResponse<Seri>>, t: Throwable) {
                view?.showToast("Tidak bisa koneksi ke server")
                view?.hideLoading()
                println(t.message)
            }

        })
    }

    override fun destroy() {
        view = null
    }
}