package com.tapptitude.kotlinmvvmandroid.di.builders

import com.tapptitude.kotlinmvvmandroid.presentation.home.fragments.SampleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindQuestionFragment(): SampleFragment
}