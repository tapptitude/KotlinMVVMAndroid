package com.tapptitude.kotlinmvvmandroid.feature.home.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.tapptitude.kotlinmvvmandroid.feature.common.viewmodel.BaseViewModel
import com.tapptitude.kotlinmvvmandroid.feature.home.domain.GetIpAddressUseCase
import com.tapptitude.kotlinmvvmandroid.feature.home.domain.GetIpAddressUseCase.ResultData

/**
 * @author Radu Dorin
 */
class HomeViewModelImpl(
    application: Application,
    private val getIpAddressUseCase: GetIpAddressUseCase
) : BaseViewModel(application), HomeViewModel {

    override val ipAddress = ObservableField<String>()
    override val isLoading = ObservableField<Boolean>()

    fun loadIpAddress() {
        getIpAddressUseCase.execute()
            .subscribe(SelfDisposingObserver { result ->
                handleDateTimeResult(result)
            })
    }

    private fun handleDateTimeResult(result: ResultData) {
        isLoading.set(result == ResultData.Loading)

        when (result) {
            is ResultData.Success -> ipAddress.set(result.data)
            is ResultData.Failure -> toastData.postValue(result.throwable.message)
        }
    }
}