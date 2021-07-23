package andi.purwanto.retribusi_android

import andi.purwanto.retribusi_android.adapters.RiwayatActivityAdapater
import andi.purwanto.retribusi_android.contracts.RiwayatActivityContract
import andi.purwanto.retribusi_android.databinding.ActivityRiwayatBinding
import andi.purwanto.retribusi_android.databinding.ActivitySeriBinding
import andi.purwanto.retribusi_android.models.Riwayat
import andi.purwanto.retribusi_android.presenters.RiwayatActivityPresenter
import andi.purwanto.retribusi_android.utilities.Constants
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

class RiwayatActivity : AppCompatActivity(), RiwayatActivityContract.RiwayatView {

    private lateinit var binding : ActivityRiwayatBinding
    private var presenter : RiwayatActivityContract.RiwayatPresenter? = null
    private lateinit var adapterRiwayat : RiwayatActivityAdapater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatBinding.inflate(layoutInflater)
        presenter = RiwayatActivityPresenter(this)
        setContentView(binding.root)
    }

    override fun attachToRecycler(riwayat: List<Riwayat>) {
        adapterRiwayat = RiwayatActivityAdapater(riwayat, this@RiwayatActivity)
        binding.tbriwayat.apply {
            adapter = adapterRiwayat
            layoutManager = LinearLayoutManager(this@RiwayatActivity)
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(this@RiwayatActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.loading.isIndeterminate = true
    }

    override fun hideLoading() {
        binding.loading.apply {
            isIndeterminate = false
            progress = 0
            visibility = View.GONE
        }
    }

    private fun getData(){
        val token = Constants.getToken(this@RiwayatActivity)
        presenter?.getRiwayat(Constants.BASIC_AUTH, token)
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }
}