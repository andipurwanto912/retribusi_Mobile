package andi.purwanto.retribusi_android

import andi.purwanto.retribusi_android.adapters.MasyarakatActivityAdapter
import andi.purwanto.retribusi_android.contracts.MasyarakatActivityContract
import andi.purwanto.retribusi_android.databinding.ActivityMasyarakatBinding
import andi.purwanto.retribusi_android.models.Masyarakat
import andi.purwanto.retribusi_android.presenters.MasyarakatActivityPresenter
import andi.purwanto.retribusi_android.utilities.Constants
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

class MasyarakatActivity : AppCompatActivity(), MasyarakatActivityContract.MasyarakatView {

    private lateinit var binding : ActivityMasyarakatBinding
    private var presenter : MasyarakatActivityContract.MasyarakatPresenter? = null
    private lateinit var adapterMasyarakat : MasyarakatActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMasyarakatBinding.inflate(layoutInflater)
        presenter = MasyarakatActivityPresenter(this)
        setContentView(binding.root)
    }

    override fun attachToRecycler(masyarakat: List<Masyarakat>) {
        adapterMasyarakat = MasyarakatActivityAdapter(masyarakat, this@MasyarakatActivity)
        binding.masyarakat.apply {
            adapter = adapterMasyarakat
            layoutManager = LinearLayoutManager(this@MasyarakatActivity)
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(this@MasyarakatActivity, message, Toast.LENGTH_LONG).show()
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
        val token = Constants.getToken(this@MasyarakatActivity)
        presenter?.getMasyarakat(Constants.BASIC_AUTH, token)
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