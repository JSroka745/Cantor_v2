package com.example.cantor.ui.main.data

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface RetroService {

    @GET("http://data.fixer.io/api/{date}")
    fun getDataFromAPI(@Path("date") date : String,@Query("access_key") key:String,@Query("symbols") symbols:String) : Single<Data>

    @GET("http://data.fixer.io/api/{date}")
    fun getFirstDataFromAPI(@Path("date") date : String,@Query("access_key") key:String,@Query("symbols") symbols:String) : Flowable<Data>
}

