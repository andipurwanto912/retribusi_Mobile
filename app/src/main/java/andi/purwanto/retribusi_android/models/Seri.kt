package andi.purwanto.retribusi_android.models

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Seri (
    @SerializedName("id_seri") var id_seri : Int? = null,
    @SerializedName("seri") var seri : String? = null,
    @SerializedName("jenis_retribusi") var jenis_retribusi : String? = null,
    @SerializedName("tagihan") var tagihan : String? = null,
) : Parcelable {
    override fun toString(): String {
        return seri!!;
    }
}
