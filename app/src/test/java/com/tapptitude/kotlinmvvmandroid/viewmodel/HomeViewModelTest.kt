package com.tapptitude.kotlinmvvmandroid.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.whenever
import com.tapptitude.kotlinmvvmandroid.feature.home.domain.GetIpAddressUseCase
import com.tapptitude.kotlinmvvmandroid.feature.home.viewmodel.HomeViewModelImpl
import io.reactivex.Observable
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import kotlin.test.assertEquals

class HomeViewModelTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()
    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @Mock
    lateinit var getIpUseCase: GetIpAddressUseCase

    @Mock
    lateinit var application: Application

    private lateinit var testViewModel: HomeViewModelImpl
    private val testIpValue = "my_test_ip_here"

    @Before
    fun setup() {
        testViewModel = HomeViewModelImpl(application, getIpUseCase)
    }

    @Test
    fun testSuccess() {
        whenever(getIpUseCase.execute()).thenReturn(
            Observable.just(
                GetIpAddressUseCase.ResultData.Success(
                    testIpValue
                )
            )
        )

        testViewModel.loadIpAddress()

        assertNotNull(testViewModel.ipAddress.get())
        assertEquals(testViewModel.ipAddress.get(), testIpValue)
    }

    @Test
    fun testFail() {
        val runtimeException = RuntimeException("my_exception")
        whenever(getIpUseCase.execute()).thenReturn(
            Observable.just(
                GetIpAddressUseCase.ResultData.Failure(runtimeException)
            )
        )
        testViewModel.loadIpAddress()

        assertNotNull(testViewModel.toastData.value)
        assertEquals(testViewModel.toastData.value, "my_exception")
    }
}