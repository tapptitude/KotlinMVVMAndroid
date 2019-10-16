package com.tapptitude.kotlinmvvmandroid.data.persistence.ip

import com.tapptitude.kotlinmvvmandroid.data.network.IpApi
import com.tapptitude.kotlinmvvmandroid.data.network.models.IpAddress
import com.tapptitude.kotlinmvvmandroid.providers.SchedulerProvider
import io.reactivex.Single

class IpNetworkRepository(
    private val ipApi: IpApi,
    private val schedulerProvider: SchedulerProvider
) : IpRepository {

    override fun loadIpAddress(): Single<IpAddress> {
        return ipApi.getIpAddress().subscribeOn(schedulerProvider.io())
    }
}