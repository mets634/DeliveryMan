package com.example.mets634.deliveryman.controller.distanceFinder.googleMaps

import android.net.Uri
import java.io.IOException
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_OK
import java.net.URL
import kotlin.reflect.KProperty

/**
 * Extension function for URL to send a GET request.
 * @return String containing result of request.
 */
fun URL.sendGet() : String {
    Logger.d("Sending GET request to URL: $this")
    val con = this.openConnection() as HttpURLConnection

    val responseCode = con.responseCode
    if (responseCode != HTTP_OK)
        throw IOException("GET request to URL: $this failed with response code: $responseCode")

    Logger.d("Received response code: $responseCode from GET request to URL: $this")
    return con.inputStream.reader().use { it.readText() }
}

/**
 * Delegation to convert to URL.
 */
class ToUrl {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): URL {
        return URL(thisRef.toString())
    }
}

/**
 * Extension property for String to get String as URL.
 */
val String.URL: URL by ToUrl()

/**
 * Extension property for Uri to get Uri as URL.
 */
val Uri.URL: URL by ToUrl()