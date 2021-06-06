package br.com.cotemig.hallison.acaimap.services

import br.com.cotemig.hallison.acaimap.model.Endereco
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIRetrofitService {

    @GET("{CEP}/json/")
    fun getEnderecoByJSON(@Path("CEP") CEP : String) : Call<Endereco>
}