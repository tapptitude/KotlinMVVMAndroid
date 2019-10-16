package com.tapptitude.kotlinmvvmandroid.data.persistence.datetime

import com.tapptitude.kotlinmvvmandroid.data.network.models.DateTime
import io.reactivex.Single

interface DateTimeRepository {

    fun loadDateTime(): Single<DateTime>
}