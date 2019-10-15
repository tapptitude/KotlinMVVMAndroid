package com.tapptitude.kotlinmvvmandroid.feature.home.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.tapptitude.kotlinmvvmandroid.feature.common.viewmodel.BaseViewModel
import com.tapptitude.kotlinmvvmandroid.feature.home.domain.GetIpAddressUseCase
import com.tapptitude.kotlinmvvmandroid.feature.home.domain.GetIpAddressUseCase.ResultData
import com.tapptitude.kotlinmvvmandroid.preferance.UserSessionManager

/**
 * @author Radu Dorin
 */
class HomeViewModelImpl(
    application: Application,
    private val getIpAddressUseCase: GetIpAddressUseCase,
    private val userSessionManager: UserSessionManager
) : BaseViewModel(application), HomeViewModel {

    override val ipAddress = ObservableField<String>()
    override val isLoading = ObservableField<Boolean>()

    override fun loadIpAddress() {
        getIpAddressUseCase.execute()
            .subscribe(SelfDisposingObserver { result ->
                handleDateTimeResult(result)
            })
    }

    private fun handleDateTimeResult(result: ResultData) {
        when (result) {
            is ResultData.Success -> {
                val stringIp = result.data
                ipAddress.set(stringIp)
                userSessionManager.saveIp(stringIp)
                isLoading.set(false)
            }
            is ResultData.Failure -> {
                toastData.postValue(result.throwable.message)
                isLoading.set(false)
            }
            ResultData.Loading -> isLoading.set(true)
        }
    }
}