package andi.purwanto.retribusi_android.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("id_user") var id_user : Int?,
    @SerializedName("nama_lengkap") var nama_lengkap : String?,
    @SerializedName("email") var email : String?,
    @SerializedName("photo") var photo : String?,
    @SerializedName("no_hp") var no_hp : String?,
    @SerializedName("api_token") var api_token : String?
) : Parcelable {
    constructor() : this(null, null, null, null, null, null)
}