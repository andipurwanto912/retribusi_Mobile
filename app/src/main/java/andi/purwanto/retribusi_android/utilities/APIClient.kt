package andi.purwanto.retribusi_android.utilities

import android.content.Context
import android.content.Context.MODE_PRIVATE
import andi.purwanto.retribusi_android.webservices.APIService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class APIClient {
    companion object {
        private var retrofit: Retrofit? = null
        private var okHttpClient = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build()

        fun APIService(): APIService = getClient().create(APIService::class.java)

        private fun getClient(): Retrofit {
            return if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(Constants.API_ENDPOINT).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build()
                retrofit!!
            } else {
                retrofit!!
            }
        }
    }
}

class Constants {
    companion object {
        const val API_ENDPOINT = "http://retribusisampah.xyz/retribusi/"
        const val BASIC_AUTH = "Basic YWRtaW46MTIzNA=="

        fun getToken(c : Context) : String {
            val s = c.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
            val token = s?.getString("TOKEN", "UNDEFINED")
            return token!!
        }

        fun setToken(context: Context, token : String){
            val pref = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
            pref.edit().apply {
                putString("TOKEN", token)
                apply()
            }
        }

        fun setList(context: Context, value: String){
            val pref = context.getSharedPreferences("USERDATA", Context.MODE_PRIVATE)
            pref.edit().apply{
                putString("USERDATA", value)
                apply()
            }
        }

        fun getList(context: Context) : String? {
            val list = context.getSharedPreferences("USERDATA", Context.MODE_PRIVATE)
            return list?.getString("USERDATA", null)
        }

        fun clearToken(context: Context){
            val pref = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
            pref.edit().clear().apply()
        }
    }
}

