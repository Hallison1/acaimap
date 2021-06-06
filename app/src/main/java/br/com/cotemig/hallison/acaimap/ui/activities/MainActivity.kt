package br.com.cotemig.hallison.acaimap.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import br.com.cotemig.hallison.acaimap.R
import br.com.cotemig.hallison.acaimap.model.Account
import br.com.cotemig.hallison.acaimap.services.RetrofitInitializer
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.Theme
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")

    lateinit var email: EditText
    lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)

        var login = findViewById<Button>(R.id.login)
        login.setOnClickListener{
            accesLogin()
        }

        var register = findViewById<TextView>(R.id.register)
        register.setOnClickListener {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        var forgot = findViewById<TextView>(R.id.forgot)
        forgot.setOnClickListener {
            var intent = Intent(this, ForgotActivity::class.java)
            startActivity(intent)
        }
    }

    fun accesLogin() {

        var account = Account()
        account.email = email.text.toString()
        account.password = password.text.toString()

        var s = RetrofitInitializer().accountService()
        var call = s.auth(account)

        call.enqueue(object : retrofit2.Callback<Account>{
            override fun onResponse(call: Call<Account>, response: Response<Account>) {
                if (response.code() == 200) {
                   showHome()
                }
            }

            override fun onFailure(call: Call<Account>, t: Throwable) {
                MaterialDialog.Builder(this@MainActivity)
                    .theme(Theme.LIGHT)
                    .title(R.string.ops)
                    .content(R.string.generic_error)
                    .positiveText(R.string.button_ok)
                    .show()
            }
        })

    }

    fun showHome() {
        var intent = Intent(this, HomeActivity::class.java)
        //intent.putExtra("email", email.text.toString())
        startActivity(intent)
        finish()
    }
}