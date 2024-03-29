package com.tapptitude.kotlinmvvmandroid.feature.common.view.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment(){

    override fun onAttach(context: Context) {
        performInjection()
        super.onAttach(context)
    }

    private fun performInjection() {
        AndroidSupportInjection.inject(this)
    }
}
