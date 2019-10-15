package com.tapptitude.kotlinmvvmandroid.feature.common.domain

/**
 * @author Radu Dorin
 */
sealed class Result<Data> {

    class Loading<Data> : Result<Data>()
    class Success<Data>(val data: Data) : Result<Data>()
    class Failure<Data>(val throwable: Throwable) : Result<Data>()
}