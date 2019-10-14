package com.tapptitude.kotlinmvvmandroid.presentation.common.viewmodel

import androidx.lifecycle.LiveData

/**
 * @author Radu Dorin
 */
interface ToastViewModel {

    val toastData: LiveData<String>
}