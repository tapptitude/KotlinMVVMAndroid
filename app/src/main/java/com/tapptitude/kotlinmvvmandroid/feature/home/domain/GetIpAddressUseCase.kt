package com.tapptitude.kotlinmvvmandroid.feature.home.domain

import com.tapptitude.kotlinmvvmandroid.data.persistence.ip.IpRepository
import com.tapptitude.kotlinmvvmandroid.feature.common.domain.Result
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

    fun execute(): Observable<Result<String>> = repository.loadIpAddress()
        .observeOn(schedulerProvider.mainThread())
        .map { Result.Success(it.ip) as Result<String> }
        .onErrorReturn { Result.Failure(it) }
        .startWith(Result.Loading())
}