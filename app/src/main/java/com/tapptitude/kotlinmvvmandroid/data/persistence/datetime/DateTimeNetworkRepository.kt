package com.tapptitude.kotlinmvvmandroid.data.persistence.datetime

import com.tapptitude.kotlinmvvmandroid.data.network.DateTimeApi
import com.tapptitude.kotlinmvvmandroid.data.network.models.DateTime
import com.tapptitude.kotlinmvvmandroid.providers.SchedulerProvider
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class DateTimeNetworkRepository(
    private val dateTimeApi: DateTimeApi,
    private val schedulerProvider: SchedulerProvider
) : DateTimeRepository {

    override fun loadDateTime(): Observable<DateTime> {
        return dateTimeApi.getDateTime()
            .delay(600, TimeUnit.MILLISECONDS)
            .subscribeOn(schedulerProvider.io())
    }
}