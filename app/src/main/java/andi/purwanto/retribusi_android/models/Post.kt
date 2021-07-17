package andi.purwanto.retribusi_android.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(@SerializedName("id_user") var id_user : Int?, @SerializedName("title") var title : String?, @SerializedName("content") var content : String?) :
    Parcelable {
    constructor() : this(null, null, null)
}