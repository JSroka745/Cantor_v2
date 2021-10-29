package com.example.cantor.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cantor.ui.main.data.Data
import com.example.cantor.ui.main.data.ListItem
import com.example.cantor.ui.main.data.RetroInstance
import com.example.cantor.ui.main.data.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    lateinit  var listData : MutableLiveData<Data>
    private lateinit var wholeDataList: MutableList<Data>
    private lateinit var listitems:MutableList<ListItem>
   // private lateinit var questionList: MutableList<Result>




    init {
        listData= MutableLiveData()
        wholeDataList= mutableListOf<Data>()
        listitems= mutableListOf<ListItem>()


    }

    fun getListItems():MutableLiveData<MutableList<ListItem>>{
        var data= MutableLiveData<MutableList<ListItem>>()
        data.value=listitems
        return data
    }

    fun makeAPICall(input:String){
        val retroInstance= RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call=retroInstance.getDataFromAPI(input)

        call.enqueue(object : Callback<Data> {
            override fun onFailure(call: Call<Data>, t: Throwable) {
                listData.postValue(null)

            }

            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if(response.isSuccessful){
                    val responsebody=response.body()
                    if (responsebody != null) {

                        Log.i("pis", "response code: "+responsebody.success.toString())


                            listData.postValue(responsebody)
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
                    }

                }
                else{
                    listData.postValue(null)
                }
            }


        })

    }
}