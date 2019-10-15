package com.tapptitude.kotlinmvvmandroid.feature.home.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.tapptitude.kotlinmvvmandroid.data.network.models.DateTime
import com.tapptitude.kotlinmvvmandroid.feature.common.domain.Result
import com.tapptitude.kotlinmvvmandroid.feature.common.viewmodel.BaseViewModel
import com.tapptitude.kotlinmvvmandroid.feature.home.domain.GetDateTimeUseCase

/**
 * @author Radu Dorin
 */
class SampleViewModelImpl(
    application: Application,
    private val dateTimeUseCase: GetDateTimeUseCase
) : BaseViewModel(application), SampleViewModel {

    override val dateTime = ObservableField<DateTime>()
    override val isLoading = ObservableField<Boolean>()

    override fun loadDateTime() {
        dateTimeUseCase.execute().subscribe(SelfDisposingObserver { result ->
            handleDateTimeResult(result)
        })
    }

    private fun handleDateTimeResult(result: Result<DateTime>) {
        when (result) {
            is Result.Success -> {
                dateTime.set(result.data)
                isLoading.set(false)
            }
            is Result.Failure -> {
                toastData.postValue(result.throwable.message)
                isLoading.set(false)
            }
            is Result.Loading -> isLoading.set(true)
        }
    }
}