package com.tapptitude.kotlinmvvmandroid.di.components

import android.app.Application
import com.tapptitude.kotlinmvvmandroid.di.builders.ActivityBuilder
import com.tapptitude.kotlinmvvmandroid.di.builders.FragmentBuilder
import com.tapptitude.kotlinmvvmandroid.di.modules.AppModule
import com.tapptitude.kotlinmvvmandroid.di.modules.NetworkModule
import com.tapptitude.kotlinmvvmandroid.di.modules.RepositoryModule
import com.tapptitude.kotlinmvvmandroid.di.modules.ViewModelFactoryModule
import com.tapptitude.kotlinmvvmandroid.utils.MVVMSampleApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MVVMSampleApp)
}
