package com.tapptitude.kotlinmvvmandroid.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.whenever
import com.tapptitude.kotlinmvvmandroid.data.network.models.DateTime
import com.tapptitude.kotlinmvvmandroid.feature.home.domain.GetDateTimeUseCase
import com.tapptitude.kotlinmvvmandroid.feature.home.viewmodel.SampleViewModelImpl
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SampleViewModelTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()
    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @Mock
    lateinit var getDataViewModel: GetDateTimeUseCase

    @Mock
    lateinit var application: Application

    private lateinit var testViewModel: SampleViewModelImpl
    private lateinit var testDateTime: DateTime

    @Before
    fun setup() {
        testDateTime = DateTime("Time", "Date", 123123123)
        testViewModel = SampleViewModelImpl(application, getDataViewModel)
    }

    @Test
    fun testSuccess() {
        whenever(getDataViewModel.execute()).thenReturn(
            Observable.just(
                GetDateTimeUseCase.ResultData.Success(
                    testDateTime
                )
            )
        )

        testViewModel.loadDateTime()

        assertNotNull(testViewModel.dateTime.get())
        assertEquals(testViewModel.dateTime.get(), testDateTime)
    }

    @Test
    fun testFail() {
        val runtimeException = RuntimeException("my_exception")
        whenever(getDataViewModel.execute()).thenReturn(
            Observable.just(
                GetDateTimeUseCase.ResultData.Failure(runtimeException)
            )
        )
        testViewModel.loadDateTime()

        assertNotNull(testViewModel.toastData.value)
        assertEquals(testViewModel.toastData.value, "my_exception")
    }
}