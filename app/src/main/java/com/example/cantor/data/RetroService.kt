package com.example.cantor.data

import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroService {

    @GET("http://data.fixer.io/api/{date}")
    fun getDataFromAPI(@Path("date") date : String,@Query("access_key") key:String,@Query("symbols") symbols:String) : Single<Data>


}

