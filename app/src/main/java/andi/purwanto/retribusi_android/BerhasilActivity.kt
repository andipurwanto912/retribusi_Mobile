package andi.purwanto.retribusi_android

import andi.purwanto.retribusi_android.databinding.ActivityBerhasilBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BerhasilActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBerhasilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBerhasilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseTotal()
    }

    private fun getName() : String? = intent.getStringExtra("NAMA")
    private fun getTotal() : String? = intent.getStringExtra("TOTAL")

    private fun parseTotal() {
        binding.tvBayar.text = getTotal()
        binding.tvNames.text = getName()
    }
}