//package andi.purwanto.retribusi_android
//
//import andi.purwanto.retribusi_android.adapters.MasyarakatActivityAdapter
//import andi.purwanto.retribusi_android.contracts.MasyarakatActivityContract
//import andi.purwanto.retribusi_android.databinding.ActivityLoginBinding.inflate
//import andi.purwanto.retribusi_android.models.Masyarakat
//import android.os.Binder
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.os.PersistableBundle
//import android.view.View
//import android.view.View.inflate
//import android.widget.Toast
//import androidx.recyclerview.widget.LinearLayoutManager
//
//class MasyarakatActivity : AppCompatActivity(), MasyarakatActivityContract.MasyarakatView {
//
//    private lateinit var binding: MasyarakatActivity
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = MasyarakatActivity.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//    }
//}