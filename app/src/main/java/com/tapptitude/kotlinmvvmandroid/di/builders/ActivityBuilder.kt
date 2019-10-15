package com.tapptitude.kotlinmvvmandroid.di.builders

import com.tapptitude.kotlinmvvmandroid.feature.home.view.activitie.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindHomeActivity(): HomeActivity
}