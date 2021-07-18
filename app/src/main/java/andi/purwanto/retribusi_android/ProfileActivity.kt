package andi.purwanto.retribusi_android

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import andi.purwanto.retribusi_android.LoginActivity
import andi.purwanto.retribusi_android.R
import andi.purwanto.retribusi_android.models.User
import andi.purwanto.retribusi_android.utilities.APIClient
import andi.purwanto.retribusi_android.utilities.Constants
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ProfileActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}
