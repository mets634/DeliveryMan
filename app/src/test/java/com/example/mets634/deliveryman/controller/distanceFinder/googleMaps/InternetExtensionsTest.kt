package com.example.mets634.deliveryman.controller.distanceFinder.googleMaps

import org.junit.Test
import org.junit.Assert

class InternetExtensionsTest {

    @Test
    fun sendGetNotEmpty() {
        Assert.assertTrue("https://google.com".URL.sendGet().isNotEmpty())
    }
}