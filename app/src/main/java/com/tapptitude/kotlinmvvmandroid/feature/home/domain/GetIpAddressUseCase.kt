package com.tapptitude.kotlinmvvmandroid.feature.home.domain

import com.tapptitude.kotlinmvvmandroid.data.persistence.ip.IpRepository
import com.tapptitude.kotlinmvvmandroid.providers.SchedulerProvider
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Radu Dorin
 */
class GetIpAddressUseCase @Inject constructor(
    private val repository: IpRepository,
    private val schedulerProvider: SchedulerProvider
) {

    sealed class ResultData {
        object Loading : ResultData()
        class Success(val data: String) : ResultData()
        class Failure(val throwable: Throwable) : ResultData()
    }

    fun execute(): Observable<ResultData> = repository.loadIpAddress()
        .observeOn(schedulerProvider.mainThread())
        .map { ResultData.Success(it.ip) as ResultData }
        .onErrorReturn { ResultData.Failure(it) }
        .startWith(ResultData.Loading)
}