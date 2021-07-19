package andi.purwanto.retribusi_android

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import andi.purwanto.retribusi_android.LoginActivity
import andi.purwanto.retribusi_android.R
import andi.purwanto.retribusi_android.contracts.LoginActivityContract
import andi.purwanto.retribusi_android.databinding.ActivityLoginBinding
import andi.purwanto.retribusi_android.models.User
import andi.purwanto.retribusi_android.utilities.APIClient
import andi.purwanto.retribusi_android.utilities.Constants
import com.google.gson.Gson

class ProfileActivity : AppCompatActivity(), LoginActivityContract.LoginView {

    private lateinit var binding : ActivityLoginBinding
//    private var presenter : LoginActivityContract.LoginPresenter? = null
//    private lateinit var adapterMasyarakat : MasyarakatActivityAdapter

     override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_profile)
         buttonClick()
         getUserData()
     }

    private fun buttonClick(){
        binding.btnKeluar.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        Constants.clearToken(requireActivity())
        startActivity(Intent(activity, LoginActivity::class.java).also {
            activity?.finish()
        })
    }

        private fun getUserData() {
            var userData = Constants.getList(requireActivity())
            var gson = Gson()

            var user = gson.fromJson(userData, User::class.java)

            binding.tvName.text = user.nama_lengkap
            binding.tvEmail.text = user.email
            binding.tvPhone.text = user.no_hp
        }
    }
