package andi.purwanto.retribusi_android.presenters

import andi.purwanto.retribusi_android.contracts.RiwayatActivityContract
import andi.purwanto.retribusi_android.models.Riwayat
import andi.purwanto.retribusi_android.responses.WrappedListResponse
import andi.purwanto.retribusi_android.utilities.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatActivityPresenter(v : RiwayatActivityContract.RiwayatView?) : RiwayatActivityContract.RiwayatPresenter {

    private var view : RiwayatActivityContract.RiwayatView? = v

    private var apiService = APIClient.APIService()

    override fun getRiwayat(token: String, rsapikey: String) {
        val request = apiService.getRiwayat(token, rsapikey)
        view?.showLoading()
        request.enqueue(object : Callback<WrappedListResponse<Riwayat>> {
            override fun onResponse(
                call: Call<WrappedListResponse<Riwayat>>,
                response: Response<WrappedListResponse<Riwayat>>
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

            override fun onFailure(call: Call<WrappedListResponse<Riwayat>>, t: Throwable) {
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