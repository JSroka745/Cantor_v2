package com.example.cantor.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cantor.data.ListItem
import com.example.cantor.data.RetroInstance
import com.example.cantor.data.RetroService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class Repository {

    companion object {
        fun newInstance() = Repository()
    }
    var listData : MutableLiveData<MutableList<ListItem>> = MutableLiveData<MutableList<ListItem>>()

    val accesskey="f44d0abaec29385f0fbcb760159236eb"
    val symbols="USD,JPY,GBP,AUD,CAD,PLN,KRW,CHF,MXN,RUB"
    var date_for_download: Calendar =Calendar.getInstance()


    fun get_actual_date_for_download(): String {
        val date = date_for_download.time

        return date.toString("yyyy-MM-dd")
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getlivedata(): MutableLiveData<MutableList<ListItem>> {
        return listData
    }



    fun makeAPICall():MutableLiveData<MutableList<ListItem>>{
        val retroInstance= RetroInstance.getRetroInstance().create(RetroService::class.java)
        var compositeDisposable = CompositeDisposable()
        val date=get_actual_date_for_download()
        var listitems:MutableList<ListItem> = mutableListOf<ListItem>()
        var  disposable : Disposable = retroInstance.getDataFromAPI(date,accesskey,symbols).subscribeOn(
            Schedulers.io())
            .subscribe(
                { responsebody->
                    if (responsebody != null) {
                        if(responsebody.success==true) {

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
                            rate = ListItem.RatesItem("CHF", responsebody.rates.CHF,date)
                            listitems.add(rate)
                            rate = ListItem.RatesItem("KRW", responsebody.rates.CHF,date)
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
                { error ->  Log.i("test", "Error: + $error") },

            )


        compositeDisposable.add(disposable)
        //disposable.dispose()

        return listData
    }
}