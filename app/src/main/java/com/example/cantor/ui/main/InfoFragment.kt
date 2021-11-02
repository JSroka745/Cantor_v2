package com.example.cantor.ui.main

import android.graphics.drawable.AnimationDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.cantor.R
import com.example.cantor.databinding.InfoFragmentBinding

class InfoFragment : Fragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }

    private lateinit var viewModel: InfoViewModel
    private lateinit var binding : InfoFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var name= arguments?.getString("name")
        var date= arguments?.getString("date")
        var rate= arguments?.getString("rate")

        binding = DataBindingUtil.inflate(inflater, R.layout.info_fragment, container, false)
        viewModel = ViewModelProvider(this).get(InfoViewModel::class.java)
        binding.infoviewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        if(name=="USD")
        {

            binding.imageView.setImageDrawable(resources.getDrawable(R.drawable.usd,null))

        }

        else if(name=="AUD")
        {

            binding.imageView.setImageDrawable(resources.getDrawable(R.drawable.aud,null))
        }

        else if(name=="CAD")
        {

            binding.imageView.setImageDrawable(resources.getDrawable(R.drawable.cad,null))
        }

        else if(name=="GBP")
        {

            binding.imageView.setImageDrawable(resources.getDrawable(R.drawable.gbp,null))
        }

        else if(name=="JPY")
        {

            binding.imageView.setImageDrawable(resources.getDrawable(R.drawable.jpy,null))
        }

        else if(name=="MXN")
        {

            binding.imageView.setImageDrawable(resources.getDrawable(R.drawable.mxn,null))
        }

        else if(name=="PLN")
        {

            binding.imageView.setImageDrawable(resources.getDrawable(R.drawable.pln,null))
        }

        else if(name=="RUB")
        {

            binding.imageView.setImageDrawable(resources.getDrawable(R.drawable.rub,null))
        }
        binding.tvDateFrinfo.text=date
        binding.tvRateFrinfo.text="1 EUR = "+rate+" "+name

        val drawable = ContextCompat.getDrawable(requireActivity(), R.drawable.gradient_info)


        binding.constraintLayout.background=drawable
        val animationDrawable =  binding.constraintLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(2000)
        animationDrawable.start()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}