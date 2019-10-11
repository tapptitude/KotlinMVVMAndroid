package com.tapptitude.kotlinmvvmandroid.data.network

import com.tapptitude.kotlinmvvmandroid.data.network.models.IpAddress
import io.reactivex.Observable
import retrofit2.http.GET

interface IpApi {

    @GET(".")
    fun getIpAddress(): Observable<IpAddress>
}