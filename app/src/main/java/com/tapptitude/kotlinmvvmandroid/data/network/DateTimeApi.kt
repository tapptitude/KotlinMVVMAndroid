package com.tapptitude.kotlinmvvmandroid.data.network

import com.tapptitude.kotlinmvvmandroid.data.network.models.DateTime
import io.reactivex.Observable
import retrofit2.http.GET

interface DateTimeApi {

    @GET(".")
    fun getDateTime(): Observable<DateTime>
}