package com.example.cantor.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cantor.ui.main.data.ListItem
import com.example.cantor.ui.main.data.RetroInstance
import com.example.cantor.ui.main.data.RetroService
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel : ViewModel() {
    var listData : MutableLiveData<MutableList<ListItem>> = MutableLiveData()

    val accesskey="780c7f66d5aec984c54a81e863831b32"
    val symbols="USD,JPY,GBP,AUD,CAD,PLN,MXN,RUB"
    var date_for_download: Calendar =Calendar.getInstance()


    fun get_actual_date_for_download(): String {
        val date = date_for_download.time

        return date.toString("yyyy-MM-dd")
    }

    fun get_two_dates_for_download():MutableList<String>{
        var mydate = date_for_download.time
        var tab= mutableListOf<String>()
        tab.add(mydate.toString("yyyy-MM-dd"))
        date_for_download.add(Calendar.DATE,-1)
        mydate=date_for_download.time
        tab.add(mydate.toString("yyyy-MM-dd"))
        date_for_download.add(Calendar.DATE,-1)
        return tab

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
                    if(responsebody.success==true) {

                        var listitems:MutableList<ListItem> = mutableListOf<ListItem>()
                        var header = ListItem.HeaderItem(responsebody.date)
                        listitems.add(header)
                        var rate = ListItem.RatesItem("USD", responsebody.rates.USD,date)
                        listitems.add(rate)
                        rate = ListItem.RatesItem("AUD", responsebody.rates.AUD,date)
                        listitems.add(rate)
                        rate = ListItem.RatesItem("CAD", responsebody.rates.CAD,date)
                        listitems.add(rate)
                        rate = ListItem.RatesItem("GBP", responsebody.rates.GBP,date)
                        listitems.add(rate)
                        rate = ListItem.RatesItem("JPY", responsebody.rates.JPY,date)
                        listitems.add(rate)
                        rate = ListItem.RatesItem("MXN", responsebody.rates.MXN,date)
                        listitems.add(rate)
                        rate = ListItem.RatesItem("PLN", responsebody.rates.PLN,date)
                        listitems.add(rate)
                        rate = ListItem.RatesItem("RUB", responsebody.rates.RUB,date)
                        listitems.add(rate)
                        listData.postValue(listitems)
                        date_for_download.add(Calendar.DATE,-1)

                    }
                        else{
                        Log.i("test", "API USAGE 100%")
                        }

                    }
                },
                { error ->  Log.i("test", "Error: + $error") }
                )




    }

    fun makeFirstAPICall(date:MutableList<String>, key:String, symbols:String){
        val retroInstance= RetroInstance.getRetroInstance().create(RetroService::class.java)
        var listitems:MutableList<ListItem> = mutableListOf<ListItem>()
        var listitems2:MutableList<ListItem> = mutableListOf<ListItem>()
        retroInstance.getFirstDataFromAPI(date[0],key,symbols).subscribeOn(Schedulers.io())
            .subscribe(
                { responsebody->
                    if (responsebody != null) {
                        if(responsebody.success==true) {


                            var header = ListItem.HeaderItem(responsebody.date)
                            listitems.add(header)
                            var rate = ListItem.RatesItem("USD", responsebody.rates.USD,date[0])
                            listitems.add(rate)
                            rate = ListItem.RatesItem("AUD", responsebody.rates.AUD,date[0])
                            listitems.add(rate)
                            rate = ListItem.RatesItem("CAD", responsebody.rates.CAD,date[0])
                            listitems.add(rate)
                            rate = ListItem.RatesItem("GBP", responsebody.rates.GBP,date[0])
                            listitems.add(rate)
                            rate = ListItem.RatesItem("JPY", responsebody.rates.JPY,date[0])
                            listitems.add(rate)
                            rate = ListItem.RatesItem("MXN", responsebody.rates.MXN,date[0])
                            listitems.add(rate)
                            rate = ListItem.RatesItem("PLN", responsebody.rates.PLN,date[0])
                            listitems.add(rate)
                            rate = ListItem.RatesItem("RUB", responsebody.rates.RUB,date[0])
                            listitems.add(rate)




                        }
                        else{
                            Log.i("test", "API USAGE 100%")
                        }

                    }
                },
                { error ->  Log.i("test", "Error: + $error") },

            )

        retroInstance.getFirstDataFromAPI(date[1],key,symbols).subscribeOn(Schedulers.io())
            .subscribe(
                { responsebody->
                    if (responsebody != null) {
                        if(responsebody.success==true) {

                            var header = ListItem.HeaderItem(responsebody.date)
                            listitems2.add(header)
                            var rate = ListItem.RatesItem("USD", responsebody.rates.USD,date[1])
                            listitems2.add(rate)
                            rate = ListItem.RatesItem("AUD", responsebody.rates.AUD,date[1])
                            listitems2.add(rate)
                            rate = ListItem.RatesItem("CAD", responsebody.rates.CAD,date[1])
                            listitems2.add(rate)
                            rate = ListItem.RatesItem("GBP", responsebody.rates.GBP,date[1])
                            listitems2.add(rate)
                            rate = ListItem.RatesItem("JPY", responsebody.rates.JPY,date[1])
                            listitems2.add(rate)
                            rate = ListItem.RatesItem("MXN", responsebody.rates.MXN,date[1])
                            listitems2.add(rate)
                            rate = ListItem.RatesItem("PLN", responsebody.rates.PLN,date[1])
                            listitems2.add(rate)
                            rate = ListItem.RatesItem("RUB", responsebody.rates.RUB,date[1])
                            listitems2.add(rate)

                        }
                        else{
                            Log.i("test", "API USAGE 100%")
                        }

                    }
                },
                { error ->  Log.i("test", "Error: + $error") },
                {
                    listitems.addAll(listitems2)
                    listData.postValue(listitems)
                }
            )

    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }


}