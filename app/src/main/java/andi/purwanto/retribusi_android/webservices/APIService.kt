package andi.purwanto.retribusi_android.webservices

import andi.purwanto.retribusi_android.models.Masyarakat
import andi.purwanto.retribusi_android.models.User
import andi.purwanto.retribusi_android.responses.WrappedListResponse
import andi.purwanto.retribusi_android.responses.WrappedResponse
import retrofit2.Call
import retrofit2.http.*

interface APIService {

    @FormUrlEncoded
    @POST("api/users/login")
    fun login(
        @Header("Authorization") token : String,
        @Header("RS-API-KEY") rsapikey : String,
        @Field("email") email : String, @Field("password") password : String) : Call<WrappedResponse<User>>

//    @FormUrlEncoded
//    @POST("api/users/register")
//    fun register(@Field("name") name : String, @Field("email") email : String, @Field("password") password : String) : Call<WrappedResponse<User>>

    @GET("api/masyarakat/masyarakat")
    fun getMasyarakat(
        @Header("Authorization") token : String,
        @Header("RS-API-KEY") rsapikey : String
    ) : Call<WrappedListResponse<Masyarakat>>
}