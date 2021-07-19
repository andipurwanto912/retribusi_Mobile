package andi.purwanto.retribusi_android

import andi.purwanto.retribusi_android.adapters.SeriActivityAdapter
import andi.purwanto.retribusi_android.contracts.SeriActivityContract
import andi.purwanto.retribusi_android.databinding.ActivitySeriBinding
import andi.purwanto.retribusi_android.models.Seri
import andi.purwanto.retribusi_android.presenters.SeriActivityPresenter
import andi.purwanto.retribusi_android.utilities.Constants
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

class SeriActivity : AppCompatActivity(), SeriActivityContract.SeriView {

    private lateinit var binding : ActivitySeriBinding
    private var presenter : SeriActivityContract.SeriPresenter? = null
    private lateinit var adapterSeri : SeriActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeriBinding.inflate(layoutInflater)
        presenter = SeriActivityPresenter(this)
        setContentView(binding.root)
    }

    override fun attachToRecycler(seri: List<Seri>) {
        adapterSeri = SeriActivityAdapter(seri, this@SeriActivity)
        binding.tagihan.apply {
            adapter = adapterSeri
            layoutManager = LinearLayoutManager(this@SeriActivity)
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(this@SeriActivity, message, Toast.LENGTH_LONG).show()
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
        val token = Constants.getToken(this@SeriActivity)
        presenter?.getSeri(Constants.BASIC_AUTH, token)
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