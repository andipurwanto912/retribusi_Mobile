package andi.purwanto.retribusi_android

import andi.purwanto.retribusi_android.contracts.TransaksiContract
import andi.purwanto.retribusi_android.databinding.ActivityTransaksiBinding
import andi.purwanto.retribusi_android.presenters.TransaksiActivityPresenter
import andi.purwanto.retribusi_android.utilities.Constants
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_transaksi.*

class TransaksiActivity : AppCompatActivity(), TransaksiContract.TransaksiView {

    private lateinit var binding : ActivityTransaksiBinding
    private var presenter : TransaksiContract.TransaksiPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransaksiBinding.inflate(layoutInflater)
        setupSpinner()
        presenter = TransaksiActivityPresenter(this)
        setupSpinnerKec()
        setupSpinnerSeri()
        setContentView(binding.root)
        doSave()
    }

    private fun doSave(){
        binding.btnTransaksi.setOnClickListener {
            showLoading()
            val basicAtuh = Constants.BASIC_AUTH
            val token = Constants.getToken(this)
            val bulan = binding.etBulan.text.toString()
            val nik = binding.etNik.text.toString()
            val nama_lengkap = binding.etName.text.toString()
            val alamat = binding.etAlamat.text.toString()
            val jml_bayar = binding.etBayar.text.toString()

            val kelurahan = binding.spinnerKelurahan.selectedItem.toString()
            val kecamatan = binding.spinnerKecamatan.selectedItem.toString()
            val seri = binding.spinnerSeri.selectedItem.toString()

            presenter?.postTransaksi(basicAtuh, token, bulan, nik, nama_lengkap, alamat, kelurahan, kecamatan, seri, jml_bayar)
        }
    }

    private fun setupSpinner(){
        val spinnerKelurahanAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(
            R.array.kelurahan
        ))

        binding.spinnerKelurahan.adapter = spinnerKelurahanAdapter
    }

    private fun setupSpinnerKec(){
        val spinnerKecamatanAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(
            R.array.kecamatan
        ))

        binding.spinnerKecamatan.adapter = spinnerKecamatanAdapter
    }

    private fun setupSpinnerSeri(){
        val spinnerSeriAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(
            R.array.seri
        ))

        binding.spinnerSeri.adapter = spinnerSeriAdapter
    }

    override fun showToast(message: String) {
        Toast.makeText(this@TransaksiActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.loadingTransaksi.isIndeterminate = true
    }

    override fun hideLoading() {
        binding.loadingTransaksi.apply {
            isIndeterminate = false
            progress = 0
            visibility = View.GONE
        }
    }

    override fun success() {
        val intent = Intent(this@TransaksiActivity, BerhasilActivity::class.java).apply {
            putExtra("TOTAL", binding.etBayar.text.toString())
        }

        startActivity(intent)
    }
}