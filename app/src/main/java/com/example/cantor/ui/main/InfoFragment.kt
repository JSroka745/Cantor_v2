package com.example.cantor.ui.main

import android.content.Context
import android.content.res.Resources
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
import com.example.cantor.viewmodels.InfoViewModel

class InfoFragment : Fragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }

    private lateinit var viewModel: InfoViewModel
    private lateinit var binding : InfoFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val name= arguments?.getString("name")
        val date= arguments?.getString("date")
        val rate= arguments?.getString("rate")

        binding = DataBindingUtil.inflate(inflater, R.layout.info_fragment, container, false)
        viewModel = ViewModelProvider(this).get(InfoViewModel::class.java)
        binding.infoviewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val drawableResId = requireContext().resIdByName(name?.lowercase(), "drawable")


        binding.imageView.setImageDrawable(resources.getDrawable(drawableResId,null))
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
    }

    fun Context.resIdByName(resIdName: String?, resType: String): Int {
        resIdName?.let {
            return resources.getIdentifier(it, resType, packageName)
        }
        throw Resources.NotFoundException()
    }
}