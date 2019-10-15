package com.tapptitude.kotlinmvvmandroid.feature.home.domain

import com.tapptitude.kotlinmvvmandroid.data.network.models.DateTime
import com.tapptitude.kotlinmvvmandroid.data.persistence.datetime.DateTimeRepository
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

    sealed class ResultData {
        object Loading : ResultData()
        class Success(val data: DateTime) : ResultData()
        class Failure(val throwable: Throwable) : ResultData()
    }

    fun execute(): Observable<ResultData> = repository.loadDateTime()
        .observeOn(schedulerProvider.mainThread())
        .map { ResultData.Success(it) as ResultData }
        .onErrorReturn { ResultData.Failure(it) }
        .startWith(ResultData.Loading)
}