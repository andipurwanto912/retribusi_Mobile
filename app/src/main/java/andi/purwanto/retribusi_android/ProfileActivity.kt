package andi.purwanto.retribusi_android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import andi.purwanto.retribusi_android.contracts.LoginActivityContract
import andi.purwanto.retribusi_android.models.User
import andi.purwanto.retribusi_android.utilities.Constants
import android.app.Activity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_profile)

         buttonClick()
         getUserData()
     }

    private fun buttonClick(){
        btnKeluar.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        Constants.clearToken(this)
        val logoutIntent = Intent(this, LoginActivity::class.java)
        startActivity(logoutIntent).also {
            finishAffinity()
        }
    }

    private fun getUserData() {
        var userData = Constants.getList(this)
        var gson = Gson()

        var user = gson.fromJson(userData, User::class.java)

        tvEmail.text = user.email
        tvName.text = user.nama_lengkap
        tvPhone.text = user.no_hp
    }
}
