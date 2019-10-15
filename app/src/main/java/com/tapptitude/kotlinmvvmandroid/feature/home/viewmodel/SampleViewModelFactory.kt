package com.tapptitude.kotlinmvvmandroid.feature.home.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tapptitude.kotlinmvvmandroid.feature.home.domain.GetDateTimeUseCase
import javax.inject.Inject

/**
 * @author Radu Dorin
 */
@Suppress("UNCHECKED_CAST")
class SampleViewModelFactory @Inject constructor(
    val application: Application,
    private val dateTimeUseCase: GetDateTimeUseCase
) : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SampleViewModelImpl::class.java)) {
            return SampleViewModelImpl(
                application,
                dateTimeUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}