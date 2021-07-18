package andi.purwanto.retribusi_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenuActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var imageviewIntent: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        imageviewIntent = findViewById(R.id.profile)
        imageviewIntent.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.profile->{
                val intentProfileActivity = Intent(this@MainMenuActivity, ProfileActivity::class.java)
            startActivity(intentProfileActivity)
            }
        }
    }
}
