package com.example.cantor.ui.main.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

companion object{
    val baseURL="http://data.fixer.io/api/2021-10-29?access_key=d7883a55be191f4c15ba6f1dd5fc7a83&symbols=USD,JPY,GBP,AUD,CAD,PLN,MXN,RUB";

    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}
}