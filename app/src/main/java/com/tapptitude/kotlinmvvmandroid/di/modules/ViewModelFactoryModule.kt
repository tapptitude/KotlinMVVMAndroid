package com.tapptitude.kotlinmvvmandroid.di.modules

import android.app.Application
import com.tapptitude.kotlinmvvmandroid.data.persistence.ip.IpRepository
import com.tapptitude.kotlinmvvmandroid.preferance.UserSessionManager
import com.tapptitude.kotlinmvvmandroid.presentation.home.viewmodel.HomeViewModelFactory
import com.tapptitude.kotlinmvvmandroid.providers.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {

    @Provides
    @Singleton
    internal fun provideHomeViewModelFactory(
        application: Application,
        repository: IpRepository,
        schedulerProvider: SchedulerProvider,
        userSessionManager: UserSessionManager
    ): HomeViewModelFactory {
        return HomeViewModelFactory(application, repository, schedulerProvider, userSessionManager)
    }
}
