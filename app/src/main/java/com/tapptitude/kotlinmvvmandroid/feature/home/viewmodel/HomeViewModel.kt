package com.tapptitude.kotlinmvvmandroid.feature.home.viewmodel

import androidx.databinding.ObservableField
import com.tapptitude.kotlinmvvmandroid.feature.common.viewmodel.ToastViewModel

/**
 * The reasoning behind having an extra layer of abstraction between the XML/View and the view model
 * is that in some cases, a view may be reused across multiple screens, and the view model should be different.
 * Thus, if having an interface for the communication, this can be easily changed with multiple implementations.
 *
 * @author Radu Dorin
 */
interface HomeViewModel : ToastViewModel {

    val ipAddress: ObservableField<String>
    val isLoading: ObservableField<Boolean>

    fun loadIpAddress()
}