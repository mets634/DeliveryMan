package com.example.mets634.deliveryman.model

import org.junit.Assert
import org.junit.Test

class HttpClientTest {

    @Test
    fun sendGet() {
        Assert.assertTrue(HttpClient().sendGet("https://google.com").isNotEmpty())
    }
}