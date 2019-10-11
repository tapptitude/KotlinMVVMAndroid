package com.tapptitude.kotlinmvvmandroid.di.builders

import com.tapptitude.kotlinmvvmandroid.presentation.home.activities.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): HomeActivity
}