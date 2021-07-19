package andi.purwanto.retribusi_android.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Riwayat (
    @SerializedName("id_transaksi") var id_transaksi : String? = null,
    @SerializedName("bulan") var bulan : String? = null,
    @SerializedName("nama_lengkap") var nama_lengkap : String? = null,
    @SerializedName("alamat") var alamat: String? = null,
    @SerializedName("kelurahan") var kelurahan : String? = null,
    @SerializedName("kecamatan") var kecamatan : String? = null,
    @SerializedName("seri") var seri : String? = null,
    @SerializedName("jml_bayar") var jml_bayar : String? = null,
    ) : Parcelable