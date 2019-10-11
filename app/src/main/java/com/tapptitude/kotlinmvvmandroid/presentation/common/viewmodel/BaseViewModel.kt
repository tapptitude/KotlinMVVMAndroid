package com.tapptitude.kotlinmvvmandroid.presentation.common.viewmodel

import android.app.Application
import androidx.annotation.CallSuper
import androidx.lifecycle.AndroidViewModel
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Radu Dorin
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application) {

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
        private inline val onSuccessAction: ((D) -> Unit)? = null,
        private inline val onErrorAction: ((Throwable) -> Unit)? = null
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