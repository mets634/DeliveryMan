package com.example.mets634.deliveryman.model

import android.net.Uri
import android.util.JsonReader
import org.json.JSONObject
import timber.log.Timber

/**
 * Class that calculates distances using the Google Maps API.
 * @see "https://developers.google.com/maps/documentation/distance-matrix/intro"
 */
class GoogleMapsDistanceFinder : DistanceFinder {

    private val BASE_URL = "http://maps.googleapis.com/maps/api/distancematrix/outputFormat?json?"
    private val ORIGINS_PARAMETER = "origins"
    private val DESTINATIONS_PARAMETER = "destinations"
    private val MODE_PARAMETER = "mode"

    private val ORIGINS_FIELD = "origin_addresses"
    private val DESTINATIONS_FIELD = "destination_addresses"

    /**
     * @see DistanceFinder.calcDistance
     */
    override fun calcDistance(
            travelMode: TravelMode,
            vararg addresses : Pair<String, String>
    ) : DistanceMatrix {

        val (origins, destinations) = addresses.unzip()

        Timber.d("""Calculating distance-matrix, with Google Maps, for:
            |Origins=$origins
            |Destinations=$destinations
            |Mode=$travelMode""".trimMargin())

        val distanceMatrixJson = queryGoogleMaps(travelMode, origins, destinations)
    }

    /**
     * A function to query Google Maps for distance matrix and send request.
     * @param travelMode The mode of travel.
     * @param origins List of origin addresses.
     * @param destinations List of destination addresses.
     * @return A string holding a JSON object resulting from query.
     */
    private fun queryGoogleMaps(
            travelMode: TravelMode,
            origins: List<String>,
            destinations: List<String>
    ) : String {

        // convert origins and destinations string format
        val originsString = origins.joinToString(separator = "|")
        val destinationString = destinations.joinToString(separator = "|")

        // construct url
        val url = Uri.parse(BASE_URL)
                .buildUpon()
                // add query parameters
                .appendQueryParameter(ORIGINS_PARAMETER, originsString)
                .appendQueryParameter(DESTINATIONS_PARAMETER, destinationString)
                .appendQueryParameter(MODE_PARAMETER, travelMode.name)
                .build() // make URI

        // send request
        return HttpClient().sendGet(url.toString())
    }

    /**
     * A function to parse JSON string returned from Google Maps API
     * to a DistanceMatrix.
     * @param response Google Maps API response.
     * @return DistanceMatrix of given JSON.
     */
    private fun parseGoogleResponse(response : String) : DistanceMatrix {
        val data = JSONObject(response)

        val origins = data[ORIGINS_FIELD]
        val destinations = data[DESTINATIONS_FIELD]
        // TODO: Not done yet
    }
}