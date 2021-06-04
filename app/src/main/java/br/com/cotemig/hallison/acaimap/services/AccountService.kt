package br.com.cotemig.hallison.acaimap.services

import br.com.cotemig.hallison.acaimap.model.Account
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AccountService {

    @POST("v1/account")
    fun create(@Body account: Account): Call<Account>

    @POST("account/forgot")
    fun forgot(@Body account: Account): Call<Void>
    // Para os casos que a API não retornar JSON nenhum utilize o Void

    @POST("account/auth")
    fun auth(@Body account: Account): Call<Account>

}