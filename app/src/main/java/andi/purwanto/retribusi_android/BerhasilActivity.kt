package andi.purwanto.retribusi_android

import andi.purwanto.retribusi_android.databinding.ActivityBerhasilBinding
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.*

class BerhasilActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBerhasilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBerhasilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseTotal()
        BackMainMenuActivity()
    }

    private fun getName() : String? = intent.getStringExtra("NAMA")
    private fun getTotal() : String? = intent.getStringExtra("TOTAL")

    private fun parseTotal() {
        binding.tvBayar.text = getTotal()
        binding.tvNames.text = getName()
    }

    private fun BackMainMenuActivity() {
        binding.backMenu.setOnClickListener {
            startActivity(Intent(this@BerhasilActivity, MainMenuActivity::class.java))
            finishAffinity()
        }
    }

//    private fun formatRupiah(number: Double): String? {
//        val localeID = Locale("in", "ID")
//        val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)
//        return formatRupiah.format(number)
//    }
}