package br.com.cotemig.hallison.acaimap.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.hallison.acaimap.R
import br.com.cotemig.hallison.acaimap.model.Endereco
import br.com.cotemig.hallison.acaimap.services.RetrofitInitializer
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.Theme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyAccountFragment : Fragment() {

    lateinit var cep_text: EditText
    lateinit var button_consultar: Button
    lateinit var cidade_account: TextView
    lateinit var uf_account: TextView
    lateinit var token: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_myaccount, container, false)
        cep_text = view.findViewById(R.id.cep_text)
        button_consultar = view.findViewById(R.id.button_consultar)
        cidade_account = view.findViewById(R.id.cidade_account)
        uf_account = view.findViewById(R.id.uf_account)
        token = view.findViewById(R.id.token)

        getAccount()
        button_consultar.setOnClickListener {
            getCEP()
        }

        return view
    }

    companion object {
        fun newInstance(): MyAccountFragment {
            return MyAccountFragment()
        }
    }

    fun getCEP(){

        val call = RetrofitInitializer().apiRetrofitServiceJSON().getEnderecoByJSON(cep_text.text.toString())
        call.enqueue(object : Callback<Endereco> {

            /* Caso a resposta seja positiva extraimos o objeto da resposta e exibimos o resultado na tela */
            override fun onResponse(call: Call<Endereco>, response: Response<Endereco>) {

                response.let {
                    val endereco: Endereco? = it.body()

                    if (endereco == null) {
                        cidade_account.text = String.format("Cep inválido!")

                    } else {
                        cidade_account.text =  String.format("Cidade: %s", endereco.localidade)
                        uf_account.text =  String.format("UF: %s", endereco.uf)
                    }
                }
            }

            override fun onFailure(call: Call<Endereco>?, t: Throwable?) {
                MaterialDialog.Builder(context!!)
                    .theme(Theme.LIGHT)
                    .title(R.string.ops)
                    .content(R.string.generic_error)
                    .positiveText(R.string.button_ok)
                    .show()
            }
        })
    }

    fun getAccount(){
    }

}