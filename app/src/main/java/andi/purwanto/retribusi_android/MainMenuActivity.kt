package andi.purwanto.retribusi_android

import andi.purwanto.retribusi_android.databinding.ActivityMainMenuBinding
import andi.purwanto.retribusi_android.utilities.Constants
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenuActivity : AppCompatActivity(){

    private lateinit var imageViewProfile: ImageView
    private lateinit var binding : ActivityMainMenuBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkLogin()
//        setContentView(R.layout.activity_main_menu)

//        imageViewProfile = findViewById(R.id.profile)
//        imageViewProfile.setOnClickListener(this)
        pindahActivity()
    }

//    override fun onClick(v: View) {
//        when(v.id){
//            R.id.profile->{
//                val intenProfileActivity = Intent(this@MainMenuActivity, ProfileActivity::class.java)
//                startActivity(intenProfileActivity)
//            }
//        }
//    }

    private fun pindahActivity(){
        binding.btnBarcode.setOnClickListener {
            startActivity(Intent(this@MainMenuActivity, BarcodeActivity::class.java))
//            startActivity(Intent(this@MainMenuActivity, Barco))
        }

        binding.btnMasyarakat.setOnClickListener {
            startActivity(Intent(this@MainMenuActivity, MasyarakatActivity::class.java))
        }

        binding.btnAbout.setOnClickListener {
            startActivity(Intent(this@MainMenuActivity, AboutActivity::class.java))
        }

        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this@MainMenuActivity, ProfileActivity::class.java))
        }

        binding.btnSeri.setOnClickListener {
            startActivity(Intent(this@MainMenuActivity, SeriActivity::class.java))
        }

        binding.btnRiwayat.setOnClickListener {
            startActivity(Intent(this@MainMenuActivity, RiwayatActivity::class.java))
        }
    }

    private fun checkLogin(){
        val token = Constants.getToken(this@MainMenuActivity)
        if(token == null || token.equals("UNDEFINED")){
            var intentToLogin = Intent(this@MainMenuActivity, LoginActivity::class.java).also{finish()}

            startActivity(intentToLogin)
        }

    }
}
