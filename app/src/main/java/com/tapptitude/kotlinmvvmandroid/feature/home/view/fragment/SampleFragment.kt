package com.tapptitude.kotlinmvvmandroid.feature.home.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tapptitude.kotlinmvvmandroid.R
import com.tapptitude.kotlinmvvmandroid.databinding.SampleFragmentBinding
import com.tapptitude.kotlinmvvmandroid.feature.common.view.fragments.BaseFragment
import com.tapptitude.kotlinmvvmandroid.feature.home.viewmodel.SampleViewModel
import com.tapptitude.kotlinmvvmandroid.feature.home.viewmodel.SampleViewModelFactory
import com.tapptitude.kotlinmvvmandroid.feature.home.viewmodel.SampleViewModelImpl
import javax.inject.Inject

class SampleFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: SampleViewModelFactory

    lateinit var viewModel: SampleViewModel
    private lateinit var binding: SampleFragmentBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SampleViewModelImpl::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Whenever observing live data from a fragment, use viewLifecycleOwner, don't use LifecycleOwner!
        // Check difference between viewLifecycleOwner and lifecycleOwner for more details.
        viewModel.toastData.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.loadDateTime()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.sample_fragment, container, false)
        return binding.root
    }
}