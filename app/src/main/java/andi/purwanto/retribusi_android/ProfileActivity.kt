package andi.purwanto.retribusi_android

import android.content.Intent
import android.os.Bundle
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

class ProfileActivity : AppCompatActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_profile)
        }
}