package com.example.mets634.deliveryman

import com.example.mets634.deliveryman.model.HttpClient
import org.junit.Assert
import org.junit.Test

class HttpClientUnitTest {
    @Test
    fun sendGet_works() {
        Assert.assertTrue(HttpClient().sendGet("https://google.com").isNotEmpty())
    }
}