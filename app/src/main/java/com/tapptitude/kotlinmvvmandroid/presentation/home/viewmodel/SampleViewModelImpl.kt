package com.tapptitude.kotlinmvvmandroid.presentation.home.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.tapptitude.kotlinmvvmandroid.data.network.models.DateTime
import com.tapptitude.kotlinmvvmandroid.data.persistence.datetime.DateTimeRepository
import com.tapptitude.kotlinmvvmandroid.presentation.common.viewmodel.BaseViewModel
import com.tapptitude.kotlinmvvmandroid.providers.SchedulerProvider

/**
 * @author Radu Dorin
 */
class SampleViewModelImpl(
    application: Application,
    private val repository: DateTimeRepository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel(application), SampleViewModel {

    override val dateTime = ObservableField<DateTime>()
    override val isLoading = ObservableField<Boolean>()

    override fun loadDateTime() {
        isLoading.set(true)
        repository.loadDateTime()
            .observeOn(schedulerProvider.mainThread())
            .doFinally {
                isLoading.set(false)
            }.subscribe(SelfDisposingObserver(onSuccessAction = { dateTime ->
                this.dateTime.set(dateTime)
            }, onErrorAction = { error ->
                toastData.postValue(error.message)
            }))
    }
}