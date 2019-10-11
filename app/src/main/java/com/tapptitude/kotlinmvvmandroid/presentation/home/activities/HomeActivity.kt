package com.tapptitude.kotlinmvvmandroid.presentation.home.activities

import android.os.Bundle
import com.tapptitude.kotlinmvvmandroid.R
import com.tapptitude.kotlinmvvmandroid.presentation.common.activities.BaseActivity
import com.tapptitude.kotlinmvvmandroid.presentation.home.fragments.SampleFragment

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpContent()
    }

    private fun setUpContent() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFL, SampleFragment())
            .commit()
    }
}
