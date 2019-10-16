package com.tapptitude.kotlinmvvmandroid.data.network

import com.tapptitude.kotlinmvvmandroid.data.network.models.DateTime
import io.reactivex.Single
import retrofit2.http.GET

interface DateTimeApi {

    @GET(".")
    fun getDateTime(): Single<DateTime>
}