package andi.purwanto.retribusi_android.presenters

import andi.purwanto.retribusi_android.contracts.TransaksiContract
import andi.purwanto.retribusi_android.responses.WrappedResponse
import andi.purwanto.retribusi_android.utilities.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransaksiActivityPresenter(v: TransaksiContract.TransaksiView?) : TransaksiContract.TransaksiPresenter {
    private var view : TransaksiContract.TransaksiView? = v
    private var apiService = APIClient.APIService()

    override fun postTransaksi(
        token: String,
        rsapikey: String,
        bulan: String,
        nik: String,
        nama_lengkap: String,
        alamat: String,
        kelurahan: String,
        kecamatan: String,
        seri: String,
        jml_bayar: String
    ) {
        val request = apiService.postPembayaran(token, rsapikey, bulan, nik, nama_lengkap, alamat, kelurahan, kecamatan, seri, jml_bayar)
        request.enqueue(object : Callback<WrappedResponse<String>>{
            override fun onResponse(
                call: Call<WrappedResponse<String>>,
                response: Response<WrappedResponse<String>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null){
                        view?.showToast(body.message)
                        view?.hideLoading()
                        view?.success()
                    }else{
                        view?.showToast("Terjadi kesalahan")
                        view?.hideLoading()
                    }
                }else{
                    view?.showToast(response.message())
                    view?.hideLoading()
                }
            }

            override fun onFailure(call: Call<WrappedResponse<String>>, t: Throwable) {
                view?.showToast("Tidak bisa koneksi ke server")
                println(t.message)
                view?.hideLoading()
            }

        })
    }

    override fun destroy() {
        view = null
    }
}