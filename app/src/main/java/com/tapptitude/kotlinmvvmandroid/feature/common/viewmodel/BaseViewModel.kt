package com.tapptitude.kotlinmvvmandroid.feature.common.viewmodel

import android.app.Application
import androidx.annotation.CallSuper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Radu Dorin
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application), ToastViewModel {

    /**
     * Mediator live data is used in order to avoid toast events being passed on subscription. Only
     * events that occur after the view is subscribed should be consumed.
     */
    override val toastData: MutableLiveData<String> = MediatorLiveData()

    private var disposables: CompositeDisposable? = null

    init {
        this.disposables = CompositeDisposable()
    }

    private fun disposeObservers() {
        disposables?.dispose()
    }

    override fun onCleared() {
        super.onCleared()
        disposeObservers()
    }

    /**
     * An observer that disposes itself on [unbind]
     * @param D
     */
    protected open inner class SelfDisposingObserver<D>(
        private inline val onErrorAction: ((Throwable) -> Unit)? = null,
        private inline val onSuccessAction: ((D) -> Unit)? = null
    ) : Observer<D> {

        /**
         * This method handles the disposing mechanism for observers.
         * If overridden, do not forget to call .super
         */
        @CallSuper
        override fun onSubscribe(d: Disposable) {
            disposables?.add(d)
        }

        /**
         * This method can be overridden is necessary.
         */
        override fun onComplete() {

        }

        override fun onNext(t: D) {
            onSuccessAction?.invoke(t)
        }

        override fun onError(e: Throwable) {
            onErrorAction?.invoke(e)
        }
    }
}