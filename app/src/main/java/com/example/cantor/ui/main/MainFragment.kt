package com.example.cantor.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.cantor.R
import com.example.cantor.databinding.MainFragmentBinding
import com.example.cantor.ui.main.data.MyRecyclerViewAdapter

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var myadapter: MyRecyclerViewAdapter
    companion object {
        fun newInstance() = MainFragment()
    }


    private var recyclerView:RecyclerView?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {



        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel


        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.makeAPICall("http://data.fixer.io/api/2021-10-29?access_key=d7883a55be191f4c15ba6f1dd5fc7a83&symbols=USD,JPY,GBP,AUD,CAD,PLN,MXN,RUB")

        recyclerView=binding.mainfragmentRecyclerView
        viewModel.getListItems().observe(viewLifecycleOwner, Observer {
//skonczyles tutajjj
            myadapter= MyRecyclerViewAdapter(viewModel.listitems)
            recyclerView!!.adapter=myadapter

        })

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}