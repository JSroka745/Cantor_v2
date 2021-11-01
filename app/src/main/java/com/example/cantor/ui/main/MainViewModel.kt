package com.example.cantor.ui.main

import android.annotation.SuppressLint
import android.util.Log
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

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var listData : MutableLiveData<MutableList<ListItem>> = MutableLiveData()
    private var wholeDataList: MutableList<Data> = mutableListOf<Data>()
    var listitems:MutableList<ListItem> = mutableListOf<ListItem>()
    val accesskey="d7883a55be191f4c15ba6f1dd5fc7a83"
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
                { responsebody ->
                    if (responsebody != null) {
                        Log.i("pis", "POBIERAM")



                        wholeDataList.add(responsebody)
                        var header= ListItem.HeaderItem(responsebody.date)
                        listitems.add(header)
                        var rate= ListItem.RatesItem("USD",responsebody.rates.USD)
                        listitems.add(rate)
                        rate= ListItem.RatesItem("AUD",responsebody.rates.AUD)
                        listitems.add(rate)
                        rate= ListItem.RatesItem("CAD",responsebody.rates.CAD)
                        listitems.add(rate)
                        rate= ListItem.RatesItem("GBP",responsebody.rates.GBP)
                        listitems.add(rate)
                        rate= ListItem.RatesItem("JPY",responsebody.rates.JPY)
                        listitems.add(rate)
                        rate= ListItem.RatesItem("MXN",responsebody.rates.MXN)
                        listitems.add(rate)
                        rate= ListItem.RatesItem("PLN",responsebody.rates.PLN)
                        listitems.add(rate)
                        rate= ListItem.RatesItem("RUB",responsebody.rates.RUB)
                        listitems.add(rate)
                        listData.postValue(listitems)
                        date_for_download.add(Calendar.DATE,-1)
                    }

                },
                { error ->  Log.i("pis", "Error: + $error") },
                {

                    Log.i("pis", "COMPLETE") })


     /*   call.enqueue(object : Callback<Data> {
            override fun onFailure(call: Call<Data>, t: Throwable) {
                listData.postValue(null)
                Log.i("pis", "ERROR: $t")
            }

            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if(response.isSuccessful){
                    val responsebody=response.body()
                    if (responsebody != null) {
                        Log.i("pis", "POBIERAM")



                            wholeDataList.add(responsebody)
                            var header= ListItem.HeaderItem(responsebody.date)
                            listitems.add(header)
                            var rate= ListItem.RatesItem("USD",responsebody.rates.USD)
                            listitems.add(rate)
                            rate= ListItem.RatesItem("AUD",responsebody.rates.AUD)
                            listitems.add(rate)
                            rate= ListItem.RatesItem("CAD",responsebody.rates.CAD)
                            listitems.add(rate)
                            rate= ListItem.RatesItem("GBP",responsebody.rates.GBP)
                            listitems.add(rate)
                            rate= ListItem.RatesItem("JPY",responsebody.rates.JPY)
                            listitems.add(rate)
                            rate= ListItem.RatesItem("MXN",responsebody.rates.MXN)
                            listitems.add(rate)
                            rate= ListItem.RatesItem("PLN",responsebody.rates.PLN)
                            listitems.add(rate)
                            rate= ListItem.RatesItem("RUB",responsebody.rates.RUB)
                            listitems.add(rate)
                            listData.value=listitems

                        date_for_download.add(Calendar.DATE,-1)
                    }

                }
                else{
                    listData.postValue(null)
                }
            }


        })*/

    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }


}