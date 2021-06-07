package br.com.cotemig.hallison.acaimap.ui.activities

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.cotemig.hallison.acaimap.R
import br.com.cotemig.hallison.acaimap.ui.fragments.HomeFragment
import br.com.cotemig.hallison.acaimap.ui.fragments.MyAccountFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var tok = intent.getStringExtra("token")
        val args = Bundle()
        args.putString("tok", tok)

        setFragment(HomeFragment.newInstance())

        var home = findViewById<ImageView>(R.id.home)
        home.setOnClickListener {
            setFragment(HomeFragment.newInstance())
        }

        var myAccount = findViewById<ImageView>(R.id.myAccount)
        myAccount.setOnClickListener {

            setFragment(MyAccountFragment.newInstance())
        }
    }

    fun setFragment(f: Fragment){
        val fl = supportFragmentManager.beginTransaction()
        fl.replace(R.id.content, f)
        fl.commit()
    }
}