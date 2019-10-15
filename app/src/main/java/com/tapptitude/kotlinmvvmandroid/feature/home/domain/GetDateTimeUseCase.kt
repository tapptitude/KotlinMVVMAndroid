package com.tapptitude.kotlinmvvmandroid.feature.home.domain

import com.tapptitude.kotlinmvvmandroid.data.network.models.DateTime
import com.tapptitude.kotlinmvvmandroid.data.persistence.datetime.DateTimeRepository
import com.tapptitude.kotlinmvvmandroid.feature.common.domain.Result
import com.tapptitude.kotlinmvvmandroid.providers.SchedulerProvider
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Radu Dorin
 */
class GetDateTimeUseCase @Inject constructor(
    private val repository: DateTimeRepository,
    private val schedulerProvider: SchedulerProvider
) {

    fun execute(): Observable<Result<DateTime>> = repository.loadDateTime()
        .observeOn(schedulerProvider.mainThread())
        .map { Result.Success(it) as Result<DateTime> }
        .onErrorReturn { Result.Failure(it) }
        .startWith(Result.Loading())
}