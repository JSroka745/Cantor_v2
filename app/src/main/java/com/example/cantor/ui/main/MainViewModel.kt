package com.example.cantor.ui.main

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cantor.ui.main.data.Data
import com.example.cantor.ui.main.data.ListItem
import com.example.cantor.ui.main.data.RetroInstance
import com.example.cantor.ui.main.data.RetroService
import io.reactivex.Observer
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.coroutineContext

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var listData : MutableLiveData<MutableList<ListItem>> = MutableLiveData()
    private var wholeDataList: MutableList<Data> = mutableListOf<Data>()
    var listitems:MutableList<ListItem> = mutableListOf<ListItem>()
    val accesskey="74f084ded0e0712bc749b9811c33b392"
    val symbols="USD,JPY,GBP,AUD,CAD,PLN,MXN,RUB"
    var date_for_download: Calendar =Calendar.getInstance()


    fun get_actual_date_for_download(): String {
        val date = date_for_download.time

        return date.toString("yyyy-MM-dd")
    }

    fun get_access_key():String{
        return accesskey
    }

    fun get_symbols():String{
        return symbols

    }




    fun makeAPICall(date:String, key:String, symbols:String){
        val retroInstance= RetroInstance.getRetroInstance().create(RetroService::class.java)
        retroInstance.getDataFromAPI(date,key,symbols).subscribeOn(Schedulers.io())
            .subscribe(
                { responsebody->
                    if (responsebody != null) {
                        Log.i("pis", "POBIERAM")
                    if(responsebody.success==true) {


                        wholeDataList.add(responsebody)
                        var header = ListItem.HeaderItem(responsebody.date)
                        listitems.add(header)
                        var rate = ListItem.RatesItem("USD", responsebody.rates.USD,get_actual_date_for_download())
                        listitems.add(rate)
                        rate = ListItem.RatesItem("AUD", responsebody.rates.AUD,get_actual_date_for_download())
                        listitems.add(rate)
                        rate = ListItem.RatesItem("CAD", responsebody.rates.CAD,get_actual_date_for_download())
                        listitems.add(rate)
                        rate = ListItem.RatesItem("GBP", responsebody.rates.GBP,get_actual_date_for_download())
                        listitems.add(rate)
                        rate = ListItem.RatesItem("JPY", responsebody.rates.JPY,get_actual_date_for_download())
                        listitems.add(rate)
                        rate = ListItem.RatesItem("MXN", responsebody.rates.MXN,get_actual_date_for_download())
                        listitems.add(rate)
                        rate = ListItem.RatesItem("PLN", responsebody.rates.PLN,get_actual_date_for_download())
                        listitems.add(rate)
                        rate = ListItem.RatesItem("RUB", responsebody.rates.RUB,get_actual_date_for_download())
                        listitems.add(rate)

                    }
                        else{
                        Log.i("pis", "Error in downloading data")
                        }

                    }
                },
                { error ->  Log.i("pis", "Error: + $error") }
                )
        date_for_download.add(Calendar.DATE,-1)
        listData.value=listitems



    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }


}