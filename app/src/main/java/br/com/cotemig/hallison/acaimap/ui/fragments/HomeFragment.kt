package br.com.cotemig.hallison.acaimap.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.hallison.acaimap.R
import br.com.cotemig.hallison.acaimap.model.Account
import br.com.cotemig.hallison.acaimap.model.Loja
import br.com.cotemig.hallison.acaimap.services.RetrofitInitializer
import br.com.cotemig.hallison.acaimap.ui.adapters.VitrineAdapter
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.Theme
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {

    lateinit var vitrineList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_home, container, false)
        vitrineList = view.findViewById(R.id.vitrineList)
        getVitrine()
        return view
    }

    fun getVitrine() {
        var s = RetrofitInitializer().vitrineService()
        var call = s.getLojas()

        call.enqueue(object : retrofit2.Callback<List<Loja>>{
            override fun onFailure(call: Call<List<Loja>>, t: Throwable) {
                MaterialDialog.Builder(context!!)
                    .theme(Theme.LIGHT)
                    .title(R.string.ops)
                    .content(R.string.generic_error)
                    .positiveText(R.string.button_ok)
                    .show()
            }

            override fun onResponse(call: Call<List<Loja>>, response: Response<List<Loja>>) {
                if (response.code() == 200) {
                    showItens(response.body())
                }
            }
        })
    }

    fun showItens(itens: List<Loja>?){
        vitrineList.layoutManager = LinearLayoutManager(activity)
        vitrineList.adapter = VitrineAdapter(itens!!, context!!)
    }

    companion object {

        fun newInstance() : HomeFragment{
            return HomeFragment()

        }

    }
}


