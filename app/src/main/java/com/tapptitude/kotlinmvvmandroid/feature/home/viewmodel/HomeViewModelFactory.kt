package com.tapptitude.kotlinmvvmandroid.feature.home.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tapptitude.kotlinmvvmandroid.feature.home.domain.GetIpAddressUseCase
import com.tapptitude.kotlinmvvmandroid.preferance.UserSessionManager
import javax.inject.Inject

/**
 * @author Radu Dorin
 */
@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory @Inject constructor(
    val application: Application,
    private val ipAddressUseCase: GetIpAddressUseCase,
    private val userSessionManager: UserSessionManager
) : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModelImpl::class.java)) {
            return HomeViewModelImpl(
                application,
                ipAddressUseCase,
                userSessionManager
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}