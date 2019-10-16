package com.tapptitude.kotlinmvvmandroid

import com.nhaarman.mockito_kotlin.whenever
import com.tapptitude.kotlinmvvmandroid.data.network.DateTimeApi
import com.tapptitude.kotlinmvvmandroid.data.network.models.DateTime
import com.tapptitude.kotlinmvvmandroid.data.persistence.datetime.DateTimeNetworkRepository
import com.tapptitude.kotlinmvvmandroid.feature.home.domain.GetDateTimeUseCase
import com.tapptitude.kotlinmvvmandroid.providers.SchedulerProvider
import com.tapptitude.kotlinmvvmandroid.util.TestSchedulerProvider
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class GetDateTimeUseCaseTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var dateTimeApi: DateTimeApi

    private lateinit var testDateTime: DateTime

    private lateinit var networkRepository: DateTimeNetworkRepository

    private lateinit var dateTimeUseCase: GetDateTimeUseCase

    private lateinit var schedulerProvider: SchedulerProvider

    @Before
    fun setup() {
        testDateTime = DateTime("Time", "Date", 123123123)
        whenever(dateTimeApi.getDateTime()).thenReturn(Single.just(testDateTime))

        schedulerProvider = TestSchedulerProvider()

        networkRepository = DateTimeNetworkRepository(dateTimeApi, schedulerProvider)
        dateTimeUseCase = GetDateTimeUseCase(networkRepository, schedulerProvider)
    }

    @Test
    fun testRepository() {
        val testObservable = networkRepository.loadDateTime()
            .test()

        testObservable.assertValue(testDateTime)
    }

    @Test
    fun testSuccess() {
        whenever(dateTimeApi.getDateTime()).thenReturn(Single.just(testDateTime))
        val testObservable = dateTimeUseCase.execute().test().await()

        testObservable.assertValueCount(2)
        testObservable.assertValueAt(1) { resultData ->
            resultData is GetDateTimeUseCase.ResultData.Success && resultData.data == testDateTime
        }
    }

    @Test
    fun testLoading() {
        whenever(dateTimeApi.getDateTime()).thenReturn(Single.just(testDateTime))
        val testObservable = dateTimeUseCase.execute().test().await()

        testObservable.assertValueCount(2)
        testObservable.assertValueAt(0, GetDateTimeUseCase.ResultData.Loading)
    }

    @Test
    fun testFail() {
        val runtimeException = RuntimeException("my_exception")
        whenever(dateTimeApi.getDateTime()).thenReturn(Single.error(runtimeException))
        val testObservable = dateTimeUseCase.execute().test().await()

        testObservable.assertValueCount(2)
        testObservable.assertValueAt(1) { resultData ->
            resultData is GetDateTimeUseCase.ResultData.Failure && resultData.throwable == runtimeException
        }
    }
}