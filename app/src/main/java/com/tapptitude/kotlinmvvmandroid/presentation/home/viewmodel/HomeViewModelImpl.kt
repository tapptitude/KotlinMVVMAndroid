package com.tapptitude.kotlinmvvmandroid.presentation.home.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.tapptitude.kotlinmvvmandroid.data.network.models.IpAddress
import com.tapptitude.kotlinmvvmandroid.data.persistence.ip.IpRepository
import com.tapptitude.kotlinmvvmandroid.preferance.UserSessionManager
import com.tapptitude.kotlinmvvmandroid.presentation.common.viewmodel.BaseViewModel
import com.tapptitude.kotlinmvvmandroid.providers.SchedulerProvider

/**
 * @author Radu Dorin
 */
class HomeViewModelImpl(
    application: Application,
    private val repository: IpRepository,
    private val schedulerProvider: SchedulerProvider,
    private val userSessionManager: UserSessionManager
) : BaseViewModel(application), HomeViewModel {

    override val ipAddress = ObservableField<String>()
    override val isLoading = ObservableField<Boolean>()

    override fun loadIpAddress() {
        isLoading.set(true)
        repository.loadIpAddress()
            .observeOn(schedulerProvider.mainThread()).doFinally {
                isLoading.set(false)
            }.subscribe(SelfDisposingObserver<IpAddress>({ ipAddress ->
                val stringIp = ipAddress.ip
                this.ipAddress.set(stringIp)
                userSessionManager.saveIp(stringIp)
            }))
    }
}