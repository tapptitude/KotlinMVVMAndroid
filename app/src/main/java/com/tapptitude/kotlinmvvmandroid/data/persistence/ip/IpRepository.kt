package com.tapptitude.kotlinmvvmandroid.data.persistence.ip

import com.tapptitude.kotlinmvvmandroid.data.network.models.IpAddress
import io.reactivex.Observable

interface IpRepository {

    fun loadIpAddress(): Observable<IpAddress>
}