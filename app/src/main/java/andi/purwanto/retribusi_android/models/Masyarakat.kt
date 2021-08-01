package andi.purwanto.retribusi_android.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Masyarakat (
    @SerializedName("id_masy") var id_masy : Int? = null,
    @SerializedName("nik") var nik : String? = null,
    @SerializedName("nama_lengkap") var nama_lengkap : String? = null,
    @SerializedName("tempat_lahir") var tempat_lahir : String? = null,
    @SerializedName("tanggal_lahir") var tanggal_lahir : String? = null,
    @SerializedName("alamat") var alamat : String? = null,
    @SerializedName("rt") var rt : String? = null,
    @SerializedName("rw") var rw : String? = null,
    @SerializedName("kelurahan") var kelurahan : String? = null,
    @SerializedName("kecamatan") var kecamatan : String? = null,
    @SerializedName("seri") var seri : String? = null
) : Parcelable