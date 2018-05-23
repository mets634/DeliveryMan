package com.example.mets634.deliveryman.model.distanceFinder

import android.net.Uri
import com.example.mets634.deliveryman.model.TravelMode
import org.json.JSONArray
import org.json.JSONObject
import timber.log.Timber
import java.lang.Thread.yield

/**
 * Class that calculates distances using the Google Maps API.
 * @see "https://developers.google.com/maps/documentation/distance-matrix/intro"
 */
object GoogleMapsDistanceFinder : DistanceFinder() {

    const val BASE_URL = "http://maps.googleapis.com/maps/api/distancematrix/outputFormat?json?"
    const val ORIGINS_PARAMETER = "origins"
    const val DESTINATIONS_PARAMETER = "destinations"
    const val MODE_PARAMETER = "mode"

    /**
     * Implementation of DistanceFinder.calcDistance
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

        val distanceMatrixJson = queryGoogleMaps(travelMode, origins, destinations) // query matrix
        return buildDistanceMatrix(distanceMatrixJson) // parse JSON
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
        val uri = Uri.parse(BASE_URL)
                .buildUpon()
                // add query parameters
                .appendQueryParameter(ORIGINS_PARAMETER, originsString)
                .appendQueryParameter(DESTINATIONS_PARAMETER, destinationString)
                .appendQueryParameter(MODE_PARAMETER, travelMode.name)
                .build()

        // send request
        return uri.URL.sendGet()
    }

    /**
     * A function to parse JSON string returned from Google Maps API
     * to a DistanceMatrix.
     * @param response Google Maps API response.
     * @return DistanceMatrix of given JSON.
     */
    private fun buildDistanceMatrix(response : String) : Int {
        val parsedResponse = parseGoogleResponse(response)

        return 0
    }
}