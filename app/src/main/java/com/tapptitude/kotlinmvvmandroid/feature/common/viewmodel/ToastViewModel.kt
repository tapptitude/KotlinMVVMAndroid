package com.tapptitude.kotlinmvvmandroid.feature.common.viewmodel

import androidx.lifecycle.LiveData

/**
 * @author Radu Dorin
 */
interface ToastViewModel {

    val toastData: LiveData<String>
}