package andi.purwanto.retribusi_android.presenters

import andi.purwanto.retribusi_android.contracts.TransaksiContract
import andi.purwanto.retribusi_android.models.Masyarakat
import andi.purwanto.retribusi_android.models.Seri
import andi.purwanto.retribusi_android.responses.WrappedListResponse
import andi.purwanto.retribusi_android.responses.WrappedResponse
import andi.purwanto.retribusi_android.utilities.APIClient
import org.json.JSONObject
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
//                    view?.showToast()
                    if(response.code()== 403){
                        view?.showToast("Anda sudah membayar bulan ini")
                    }else{
                        view?.showToast("NIK tidak valid!")
                    }
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

    override fun getMasyarakatByNik(token: String, rsapikey: String, nik: String) {
        val request = apiService.getMasyarakatByNik(token, rsapikey, nik)
        request.enqueue(object : Callback<WrappedListResponse<Masyarakat>>{
            override fun onResponse(
                call: Call<WrappedListResponse<Masyarakat>>,
                response: Response<WrappedListResponse<Masyarakat>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        view?.fill(body.data)
                    }
                }else{
                    view?.showToast(response.message())
                }
            }

            override fun onFailure(call: Call<WrappedListResponse<Masyarakat>>, t: Throwable) {
                view?.showToast("Tidak bisa koneksi ke server")
                println(t.message)
            }

        })
    }

    override fun getSeri(token: String, rsapikey: String) {
        val request = apiService.getSeri(token, rsapikey)
        request.enqueue(object :  Callback<WrappedListResponse<Seri>> {
            override fun onResponse(
                call: Call<WrappedListResponse<Seri>>,
                response: Response<WrappedListResponse<Seri>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null){
                        view?.attachSeri(body.data)
                    }
                }
            }

            override fun onFailure(call: Call<WrappedListResponse<Seri>>, t: Throwable) {
                view?.showToast("Tidak bisa koneksi ke server")
            }

        })
    }

    override fun destroy() {
        view = null
    }
}