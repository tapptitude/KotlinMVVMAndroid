package com.tapptitude.kotlinmvvmandroid.presentation.home.activitie

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.tapptitude.kotlinmvvmandroid.R
import com.tapptitude.kotlinmvvmandroid.databinding.HomeActivityBinding
import com.tapptitude.kotlinmvvmandroid.presentation.common.activities.BaseActivity
import com.tapptitude.kotlinmvvmandroid.presentation.home.fragment.SampleFragment
import com.tapptitude.kotlinmvvmandroid.presentation.home.viewmodel.HomeViewModel
import com.tapptitude.kotlinmvvmandroid.presentation.home.viewmodel.HomeViewModelFactory
import com.tapptitude.kotlinmvvmandroid.presentation.home.viewmodel.HomeViewModelImpl
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory

    lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.home_activity)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModelImpl::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.loadIpAddress()
        setUpContent()
    }

    private fun setUpContent() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFL, SampleFragment())
            .commit()
    }
}
