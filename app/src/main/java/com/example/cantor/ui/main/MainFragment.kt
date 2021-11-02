package com.example.cantor.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cantor.R
import com.example.cantor.databinding.MainFragmentBinding
import com.example.cantor.ui.main.data.MyRecyclerViewAdapter
import android.widget.Toast
import com.example.cantor.ui.main.data.ListItem


class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var myadapter: MyRecyclerViewAdapter
    companion object {
        fun newInstance() = MainFragment()
    }


    private var recyclerView:RecyclerView?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {



        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel


        binding.lifecycleOwner = viewLifecycleOwner



        recyclerView=binding.mainfragmentRecyclerView


        viewModel.listData.observe(viewLifecycleOwner, Observer {
            myadapter= MyRecyclerViewAdapter(it,this@MainFragment::onItemClickHandler)
            recyclerView!!.adapter=myadapter
            recyclerView!!.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)

            myadapter.notifyDataSetChanged()
        })



        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    //Toast.makeText(context, "Last", Toast.LENGTH_LONG).show()
                    viewModel.makeAPICall(viewModel.get_actual_date_for_download(),viewModel.get_access_key(),viewModel.get_symbols())

                }
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.makeAPICall(viewModel.get_actual_date_for_download(),viewModel.get_access_key(),viewModel.get_symbols())
        // TODO: Use the ViewModel
    }

    private fun onItemClickHandler(item:ListItem.RatesItem){

        var name = item.name
        var rate=item.value
        var date = item.date
        var bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("date", date)
        bundle.putString("rate", rate.toString())
        val frag = InfoFragment.newInstance()
        this.viewModelStore.clear()
        frag.arguments = bundle
        val transaction = requireActivity().supportFragmentManager.beginTransaction().addToBackStack("first")
        transaction.replace((((view?.parent)) as ViewGroup).id, frag)
        transaction.commit()

    }

}