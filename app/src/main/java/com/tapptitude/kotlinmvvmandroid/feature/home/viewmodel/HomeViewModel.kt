package com.tapptitude.kotlinmvvmandroid.feature.home.viewmodel

import androidx.databinding.ObservableField
import com.tapptitude.kotlinmvvmandroid.feature.common.viewmodel.ToastViewModel

/**
 * @author Radu Dorin
 */
interface HomeViewModel : ToastViewModel {

    val ipAddress: ObservableField<String>
    val isLoading: ObservableField<Boolean>

    fun loadIpAddress()
}