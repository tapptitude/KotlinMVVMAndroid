package com.tapptitude.kotlinmvvmandroid.feature.home.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.tapptitude.kotlinmvvmandroid.data.network.models.DateTime
import com.tapptitude.kotlinmvvmandroid.feature.common.viewmodel.BaseViewModel
import com.tapptitude.kotlinmvvmandroid.feature.home.domain.GetDateTimeUseCase
import com.tapptitude.kotlinmvvmandroid.feature.home.domain.GetDateTimeUseCase.ResultData
import com.tapptitude.kotlinmvvmandroid.feature.home.domain.GetDateTimeUseCase.ResultData.*


/**
 * @author Radu Dorin
 */
class SampleViewModelImpl(
    application: Application,
    private val dateTimeUseCase: GetDateTimeUseCase
) : BaseViewModel(application), SampleViewModel {

    override val dateTime = ObservableField<DateTime>()
    override val isLoading = ObservableField<Boolean>()

    fun loadDateTime() {
        dateTimeUseCase.execute()
            .subscribe(SelfDisposingObserver { result ->
                handleDateTimeResult(result)
            })
    }

    private fun handleDateTimeResult(result: ResultData) {
        isLoading.set(result == Loading)
        when (result) {
            is Success -> dateTime.set(result.data)
            is Failure -> toastData.postValue(result.throwable.message)
        }
    }
}