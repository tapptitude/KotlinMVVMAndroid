package com.tapptitude.kotlinmvvmandroid.presentation.home.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tapptitude.kotlinmvvmandroid.data.persistence.ip.IpRepository
import com.tapptitude.kotlinmvvmandroid.preferance.UserSessionManager
import com.tapptitude.kotlinmvvmandroid.providers.SchedulerProvider
import javax.inject.Inject

/**
 * @author Radu Dorin
 */
@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory @Inject constructor(
    val application: Application,
    private val repository: IpRepository,
    private val schedulerProvider: SchedulerProvider,
    private val userSessionManager: UserSessionManager
) : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModelImpl::class.java)) {
            return HomeViewModelImpl(
                application,
                repository,
                schedulerProvider,
                userSessionManager
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}