package com.tapptitude.kotlinmvvmandroid.data.persistence.datetime

import com.tapptitude.kotlinmvvmandroid.data.network.models.DateTime
import io.reactivex.Observable

interface DateTimeRepository {

    fun loadDateTime(): Observable<DateTime>
}