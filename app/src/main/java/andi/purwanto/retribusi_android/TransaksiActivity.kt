package andi.purwanto.retribusi_android

import andi.purwanto.retribusi_android.contracts.TransaksiContract
import andi.purwanto.retribusi_android.databinding.ActivityTransaksiBinding
import andi.purwanto.retribusi_android.models.Masyarakat
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

//    private val bulan = MutableLiveData<String>()
////    private val nik = MutableLiveData<String>()
////    private val nama_lengkap = MutableLiveData<String>()
//    private val jml_bayar = MutableLiveData<String>()
////    private val alamat = MutableLiveData<String>()
//    private val isValidLiveData = MediatorLiveData<Boolean>().apply { MediatorLiveData<Boolean>
//        addSource(bulan){ email ->
//            val jml_bayar = jml_bayar.value
//            this.value = validateForm(bulan, jml_bayar)

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

    private fun validate(){
        if (binding.etBulan.text.toString().isEmpty()){
            binding.errorTanggal.text = "Bulan harus diisi"
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

    override fun fill(masyarakat: List<Masyarakat>) {
        if(getItScan()){
            val spinnerKelurahanAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(
                R.array.kelurahan
            ))
            val spinnerSeriAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(
                R.array.seri
            ))
            val selectedKelurahan = spinnerKelurahanAdapter.getPosition(masyarakat[0].kelurahan)
            binding.spinnerKelurahan.setSelection(selectedKelurahan)

            val selectedSeri = spinnerSeriAdapter.getPosition(masyarakat[0].seri)
            binding.spinnerSeri.setSelection(selectedSeri)

            binding.etNik.setText(masyarakat[0].nik)
            binding.etName.setText(masyarakat[0].nama_lengkap)
            binding.etAlamat.setText(masyarakat[0].alamat)

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
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

}