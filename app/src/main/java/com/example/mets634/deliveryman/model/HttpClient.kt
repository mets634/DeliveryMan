package com.example.mets634.deliveryman.model

import timber.log.Timber
import java.io.IOException
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_OK
import java.net.URL

/**
 * A class to make GET requests through HTTP protocol.
 */
class HttpClient {

    /**
     * Function to make GET request to given URL.
     * @param url URL to request.
     */
    fun sendGet(url : String) : String {
        Timber.d("Sending GET request to URL: $url")
        val obj = URL(url)
        val con = obj.openConnection() as HttpURLConnection

        val responseCode = con.responseCode
        if (responseCode != HTTP_OK)
            throw IOException("GET request to URL: $url failed with response code: $responseCode")

        Timber.d("Received response code: $responseCode from GET request to URL: $url")
        return con.inputStream.reader().use { it.readText() }
    }
}
