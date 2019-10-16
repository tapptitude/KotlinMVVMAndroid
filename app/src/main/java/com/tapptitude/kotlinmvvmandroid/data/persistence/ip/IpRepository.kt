package com.tapptitude.kotlinmvvmandroid.data.persistence.ip

import com.tapptitude.kotlinmvvmandroid.data.network.models.IpAddress
import io.reactivex.Single

interface IpRepository {

    fun loadIpAddress(): Single<IpAddress>
}