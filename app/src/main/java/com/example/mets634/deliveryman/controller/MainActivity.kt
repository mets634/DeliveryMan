package com.example.mets634.deliveryman.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.mets634.deliveryman.BuildConfig
import com.example.mets634.deliveryman.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        Timber.d("Configured Timber for logging")
    }
}
