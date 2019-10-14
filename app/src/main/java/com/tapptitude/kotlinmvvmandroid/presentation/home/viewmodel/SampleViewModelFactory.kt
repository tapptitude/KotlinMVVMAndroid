package com.tapptitude.kotlinmvvmandroid.presentation.home.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tapptitude.kotlinmvvmandroid.data.persistence.datetime.DateTimeRepository
import com.tapptitude.kotlinmvvmandroid.providers.SchedulerProvider
import javax.inject.Inject

/**
 * @author Radu Dorin
 */
@Suppress("UNCHECKED_CAST")
class SampleViewModelFactory @Inject constructor(
    val application: Application,
    private val dateTimeRepository: DateTimeRepository,
    private val schedulerProvider: SchedulerProvider
) : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SampleViewModelImpl::class.java)) {
            return SampleViewModelImpl(
                application,
                dateTimeRepository,
                schedulerProvider
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}