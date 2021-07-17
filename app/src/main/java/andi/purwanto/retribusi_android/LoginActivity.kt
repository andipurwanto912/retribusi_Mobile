package andi.purwanto.retribusi_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import andi.purwanto.retribusi_android.contracts.LoginActivityContract
import andi.purwanto.retribusi_android.presenters.LoginActivityPresenter
import andi.purwanto.retribusi_android.utilities.Constants
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), LoginActivityContract.LoginView {
    private var presenter : LoginActivityContract.LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        presenter = LoginActivityPresenter(this)
        doLogin()
    }

    private fun doLogin(){
        btn_masuk.setOnClickListener {
            val e = et_email.text.toString().trim()
            val p = et_password.text.toString().trim()
            if(e.isNotEmpty() && p.isNotEmpty()){
                if(p.length > 8){
                    presenter?.login("Basic YWRtaW46MTIzNA==","RS@KEY",e, p, this@LoginActivity)
                }else{
                    showToast("Password must be 8 characters or more")
                }
            }else{
                showToast("Please fill all forms first")
            }
        }

//        btn_register.setOnClickListener { startActivity(Intent(this@LoginActivity, RegisterActivity::class.java)) }
    }

    override fun showToast(message: String) = Toast.makeText(this@LoginActivity, message, Toast.LENGTH_LONG).show()

    override fun successLogin() {
        startActivity(Intent(this@LoginActivity, MainMenuActivity::class.java).also {
            finish()
        })
    }

    override fun showLoading() {
        btn_masuk.isEnabled = false
//        btn_register.isEnabled = false
        loading.apply {
            isIndeterminate = true
        }
    }

    override fun hideLoading() {
//        btn_register.isEnabled = true
        btn_masuk.isEnabled = true
        loading.apply {
            isIndeterminate = false
            progress = 0
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

    override fun onResume() {
        super.onResume()
        val token = Constants.getToken(this@LoginActivity)
        if(!token.equals("UNDEFINED")){
            startActivity(Intent(this@LoginActivity, MainMenuActivity::class.java).also { finish() })
        }
    }
}