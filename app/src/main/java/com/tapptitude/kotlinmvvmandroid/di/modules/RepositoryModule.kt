package com.tapptitude.kotlinmvvmandroid.di.modules

import com.tapptitude.kotlinmvvmandroid.data.network.DateTimeApi
import com.tapptitude.kotlinmvvmandroid.data.network.IpApi
import com.tapptitude.kotlinmvvmandroid.data.persistence.datetime.DateTimeNetworkRepository
import com.tapptitude.kotlinmvvmandroid.data.persistence.datetime.DateTimeRepository
import com.tapptitude.kotlinmvvmandroid.data.persistence.ip.IpNetworkRepository
import com.tapptitude.kotlinmvvmandroid.data.persistence.ip.IpRepository
import com.tapptitude.kotlinmvvmandroid.providers.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun provideDateTimeRepository(
        dateTimeApi: DateTimeApi,
        schedulerProvider: SchedulerProvider
    ): DateTimeRepository {
        return DateTimeNetworkRepository(dateTimeApi, schedulerProvider)
    }

    @Provides
    @Singleton
    internal fun provideIpRepository(
        ipApi: IpApi,
        schedulerProvider: SchedulerProvider
    ): IpRepository {
        return IpNetworkRepository(ipApi, schedulerProvider)
    }
}
