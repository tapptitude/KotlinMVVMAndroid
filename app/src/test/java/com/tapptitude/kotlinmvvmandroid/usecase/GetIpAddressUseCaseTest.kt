package com.tapptitude.kotlinmvvmandroid.usecase

import com.nhaarman.mockito_kotlin.whenever
import com.tapptitude.kotlinmvvmandroid.data.network.IpApi
import com.tapptitude.kotlinmvvmandroid.data.network.models.IpAddress
import com.tapptitude.kotlinmvvmandroid.data.persistence.ip.IpNetworkRepository
import com.tapptitude.kotlinmvvmandroid.data.persistence.ip.IpRepository
import com.tapptitude.kotlinmvvmandroid.feature.home.domain.GetIpAddressUseCase
import com.tapptitude.kotlinmvvmandroid.preferance.UserSessionManager
import com.tapptitude.kotlinmvvmandroid.providers.SchedulerProvider
import com.tapptitude.kotlinmvvmandroid.util.TestSchedulerProvider
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class GetIpAddressUseCaseTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var ipApi: IpApi

    @Mock
    lateinit var userSessionManager: UserSessionManager

    private lateinit var networkRepository: IpRepository

    private lateinit var testIp: IpAddress

    private lateinit var getIpUseCase: GetIpAddressUseCase

    private lateinit var schedulerProvider: SchedulerProvider

    private val testIpValue = "my_test_ip_here"

    @Before
    fun setup() {
        testIp = IpAddress(testIpValue)

        whenever(ipApi.getIpAddress()).thenReturn(Single.just(testIp))

        schedulerProvider = TestSchedulerProvider()

        networkRepository = IpNetworkRepository(ipApi, schedulerProvider)

        getIpUseCase = GetIpAddressUseCase(networkRepository, schedulerProvider, userSessionManager)
    }

    @Test
    fun testRepository() {
        val testObservable = networkRepository.loadIpAddress()
            .test()

        testObservable.assertValue(testIp)
    }

    @Test
    fun testSuccess() {
        whenever(ipApi.getIpAddress()).thenReturn(Single.just(testIp))
        val testObservable = getIpUseCase.execute().test().await()

        testObservable.assertValueCount(2)
        testObservable.assertValueAt(1) { resultData ->
            resultData is GetIpAddressUseCase.ResultData.Success && resultData.data == testIp.ip
        }
    }

    @Test
    fun testLoading() {
        whenever(ipApi.getIpAddress()).thenReturn(Single.just(testIp))
        val testObservable = getIpUseCase.execute().test().await()

        testObservable.assertValueCount(2)
        testObservable.assertValueAt(0, GetIpAddressUseCase.ResultData.Loading)
    }

    @Test
    fun testFail() {
        val runtimeException = RuntimeException("my_exception")
        whenever(ipApi.getIpAddress()).thenReturn(Single.error(runtimeException))
        val testObservable = getIpUseCase.execute().test().await()

        testObservable.assertValueCount(2)
        testObservable.assertValueAt(1) { resultData ->
            resultData is GetIpAddressUseCase.ResultData.Failure && resultData.throwable == runtimeException
        }
    }
}