package com.tapptitude.kotlinmvvmandroid.feature.home.viewmodel

import androidx.databinding.ObservableField
import com.tapptitude.kotlinmvvmandroid.data.network.models.DateTime
import com.tapptitude.kotlinmvvmandroid.feature.common.viewmodel.ToastViewModel

/**
 * @author Radu Dorin
 */
interface SampleViewModel : ToastViewModel {

    val dateTime: ObservableField<DateTime>
    val isLoading: ObservableField<Boolean>

    fun loadDateTime()
}