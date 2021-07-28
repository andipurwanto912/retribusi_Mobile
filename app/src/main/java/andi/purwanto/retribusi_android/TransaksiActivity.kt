package andi.purwanto.retribusi_android

import andi.purwanto.retribusi_android.contracts.TransaksiContract
import andi.purwanto.retribusi_android.databinding.ActivityTransaksiBinding
import andi.purwanto.retribusi_android.models.Masyarakat
import andi.purwanto.retribusi_android.models.Seri
import andi.purwanto.retribusi_android.presenters.TransaksiActivityPresenter
import andi.purwanto.retribusi_android.utilities.Constants
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.android.synthetic.main.activity_transaksi.*

class TransaksiActivity : AppCompatActivity(), TransaksiContract.TransaksiView {

    private lateinit var binding : ActivityTransaksiBinding
    private var presenter : TransaksiContract.TransaksiPresenter? = null
    private var seriMasyarakat : String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransaksiBinding.inflate(layoutInflater)
        setupSpinner()
        presenter = TransaksiActivityPresenter(this)
        setupSpinnerKec()
        setContentView(binding.root)
        doSave()
        BackMainMenuActivity()
    }

    private fun validate(){
        if (binding.etBulan.text.toString().isEmpty()){
            binding.etBulan.error
        }
    }

    private fun getItScan() : Boolean = intent.getBooleanExtra("ITS_SCAN", false)

    private fun getData(){
        if(getItScan()){
            val nik = intent.getStringExtra("NIK")
            val basicAuth = Constants.BASIC_AUTH
            val token = Constants.getToken(this)
            presenter?.getMasyarakatByNik(basicAuth, token, nik!!)
        }
    }

    private fun doSave(){
        binding.btnTransaksi.setOnClickListener {
            if(binding.etBulan.text.isEmpty()){
                Toast.makeText(this@TransaksiActivity, "Isikan form bulan tahun terlebih dahulu", Toast.LENGTH_LONG)
                    .show()
            }else{
                showLoading()
                validate()
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

    private fun BackMainMenuActivity() {
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this@TransaksiActivity, MainMenuActivity::class.java))
//            startActivity(Intent(this@MainMenuActivity, Barco))
            finishAffinity()
        }
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

    override fun fill(masyarakat: List<Masyarakat>) {
        if(getItScan()){
            val spinnerKelurahanAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(
                R.array.kelurahan
            ))

            val selectedKelurahan = spinnerKelurahanAdapter.getPosition(masyarakat[0].kelurahan)
            binding.spinnerKelurahan.setSelection(selectedKelurahan)


            binding.etNik.setText(masyarakat[0].nik)
            binding.etName.setText(masyarakat[0].nama_lengkap)
            binding.etAlamat.setText(masyarakat[0].alamat)
            seriMasyarakat = masyarakat[0].seri.toString()
        }
    }

    private fun getSeri(){
        val token = Constants.getToken(this@TransaksiActivity)
        presenter?.getSeri(Constants.BASIC_AUTH, token)

    }


    override fun attachSeri(series: List<Seri>) {
        if(getItScan()){
            val spinnerSeriAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, series)
            binding.spinnerSeri.adapter = spinnerSeriAdapter

            for(seri in series.indices){
                if(series[seri].seri == seriMasyarakat){
                    binding.spinnerSeri.setSelection(seri)
                }
            }

            val objectSeries = binding.spinnerSeri.selectedItem as Seri
            binding.etBayar.setText(objectSeries.tagihan)
        }
    }

    override fun success() {
        val intent = Intent(this@TransaksiActivity, BerhasilActivity::class.java).apply {
            putExtra("TOTAL", binding.etBayar.text.toString())
            putExtra("NAMA", binding.etName.text.toString())
        }

        startActivity(intent).also {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
        getSeri()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

}