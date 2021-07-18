//package andi.purwanto.retribusi_android.presenters
//
//import andi.purwanto.retribusi_android.contracts.MasyarakatActivityContract
//import andi.purwanto.retribusi_android.models.Masyarakat
//import andi.purwanto.retribusi_android.responses.WrappedListResponse
//import andi.purwanto.retribusi_android.utilities.APIClient
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class MasyarakatActivityPresenter(v : MasyarakatActivityContract.MasyarakatView?) : MasyarakatActivityContract.MasyarakatPresenter {
//
//    private var view : MasyarakatActivityContract.MasyarakatView? = v
//
//    private var apiService = APIClient.APIService()
//
//    override fun getMasyarakat(token: String, rsapikey: String) {
//        val request = apiService.getMasyarakat(token, rsapikey)
//        view?.showLoading()
//        request.enqueue(object : Callback<WrappedListResponse<Masyarakat>> {
//            override fun onResponse(
//                call: Call<WrappedListResponse<Masyarakat>>,
//                response: Response<WrappedListResponse<Masyarakat>>
//            ) {
//                println("RESPONSE " + response)
//                if(response.isSuccessful){
//                    val body = response.body()
//                    if(body != null){
//                        view?.attachToRecycler(body.data)
//                        view?.hideLoading()
//                    }else{
//                        view?.showToast("Terjadi kesalahan")
//                        view?.hideLoading()
//                    }
//                }else{
//                    view?.showToast("Body Kosong")
//                    view?.hideLoading()
//                }
//            }
//
//            override fun onFailure(call: Call<WrappedListResponse<Masyarakat>>, t: Throwable) {
//                view?.showToast("Tidak bisa koneksi ke server")
//                view?.hideLoading()
//                println(t.message)
//            }
//
//        })
//    }
//
//    override fun destroy() {
//        view = null
//    }
//}