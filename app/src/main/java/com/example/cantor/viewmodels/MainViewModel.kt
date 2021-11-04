package com.example.cantor.viewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cantor.data.ListItem
import com.example.cantor.data.RetroInstance
import com.example.cantor.data.RetroService
import com.example.cantor.repository.Repository
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


class MainViewModel : ViewModel() {
    var listData : MutableLiveData<MutableList<ListItem>> = MutableLiveData<MutableList<ListItem>>()
    private var repository:Repository = Repository.newInstance()





    fun get_data(){


        listData.value=repository.makeAPICall().value
        if(listData.value==null)
        {
            listData.value=repository.getlivedata().value
        }
      //  Log.i("test","get data: "+z.size)
    }






}