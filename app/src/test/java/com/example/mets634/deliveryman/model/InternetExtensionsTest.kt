package com.example.mets634.deliveryman.model

import com.example.mets634.deliveryman.model.distanceFinder.URL
import com.example.mets634.deliveryman.model.distanceFinder.sendGet
import org.junit.Assert
import org.junit.Test

class InternetExtensionsTest {

    @Test
    fun sendGet() {
        Assert.assertTrue("https://google.com".URL.sendGet().isNotEmpty())
    }
}