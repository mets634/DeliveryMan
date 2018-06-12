package com.example.mets634.deliveryman.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.mets634.deliveryman.R



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Logger.d("Configured Timber for logging")
    }
}
