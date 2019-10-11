package com.tapptitude.kotlinmvvmandroid.providers

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun mainThread(): Scheduler
}