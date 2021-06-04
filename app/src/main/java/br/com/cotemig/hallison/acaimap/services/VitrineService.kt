package br.com.cotemig.hallison.acaimap.services

import br.com.cotemig.hallison.acaimap.model.Loja
import retrofit2.Call
import retrofit2.http.GET

interface VitrineService {

    @GET("1ae7ac40-088a-425f-b94e-19eaf2451e1e")
    fun getLojas(): Call<List<Loja>>
}