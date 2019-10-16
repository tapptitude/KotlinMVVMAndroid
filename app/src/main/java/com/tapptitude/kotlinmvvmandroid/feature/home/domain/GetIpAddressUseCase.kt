package com.tapptitude.kotlinmvvmandroid.feature.home.domain

import com.tapptitude.kotlinmvvmandroid.data.network.models.IpAddress
import com.tapptitude.kotlinmvvmandroid.data.persistence.ip.IpRepository
import com.tapptitude.kotlinmvvmandroid.preferance.UserSessionManager
import com.tapptitude.kotlinmvvmandroid.providers.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.SingleTransformer
import javax.inject.Inject

/**
 * @author Radu Dorin
 */
class GetIpAddressUseCase @Inject constructor(
    private val repository: IpRepository,
    private val schedulerProvider: SchedulerProvider,
    private val userSessionManager: UserSessionManager
) {

    sealed class ResultData {
        object Loading : ResultData()
        class Success(val data: String) : ResultData()
        class Failure(val throwable: Throwable) : ResultData()
    }

    fun execute(): Observable<ResultData> = repository.loadIpAddress()
        .compose(saveIpAddress())
        .toObservable()
        .observeOn(schedulerProvider.mainThread())
        .map { ResultData.Success(it) as ResultData }
        .onErrorReturn { ResultData.Failure(it) }
        .startWith(ResultData.Loading)

    private fun saveIpAddress(): SingleTransformer<IpAddress, String> {
        return SingleTransformer {
            it.map { ipAddress ->
                val stringIp = ipAddress.ip
                userSessionManager.saveIp(stringIp)
                stringIp
            }
        }
    }
}