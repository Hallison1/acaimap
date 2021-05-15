package br.com.cotemig.hallison.acaimap.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder().
    baseUrl("https://api.fluo.work/v1/").
    addConverterFactory(GsonConverterFactory.create()).
    build()

    fun accountService() : AccountService{
        return retrofit.create(AccountService::class.java)
    }
}