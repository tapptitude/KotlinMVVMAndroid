package com.tapptitude.kotlinmvvmandroid.di.builders

import com.tapptitude.kotlinmvvmandroid.feature.home.view.fragment.SampleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindSampleFragment(): SampleFragment
}