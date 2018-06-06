package com.example.mets634.deliveryman.model.distanceFinder.googleMaps

import android.net.Uri
import com.example.mets634.deliveryman.model.CostMatrix
import com.example.mets634.deliveryman.model.distanceFinder.TravelMode
import com.example.mets634.deliveryman.model.distanceFinder.DistanceFinder
import com.example.mets634.deliveryman.model.distanceFinder.DistanceType
import com.example.mets634.deliveryman.model.product
import timber.log.Timber

/**
 * Class that calculates distances using the Google Maps API.
 * @see "https://developers.google.com/maps/documentation/distance-matrix/intro"
 */
object GoogleMapsDistanceFinder : DistanceFinder {

    const val BASE_URL = "http://maps.googleapis.com/maps/api/distancematrix/json?"
    const val ORIGINS_PARAMETER = "origins"
    const val DESTINATIONS_PARAMETER = "destinations"
    const val MODE_PARAMETER = "mode"

    /**
     * Implementation of GoogleMapsDistanceFinder.calcDistance
     * @see DistanceFinder.calcDistanceMatrix
     */
    override fun calcDistanceMatrix(
            distanceType: DistanceType,
            travelMode: TravelMode,
            origins: List<String>,
            destinations: List<String>
    ) : CostMatrix<String> {

        Timber.d("""Calculating distance-matrix, with Google Maps, for:
            |Origins=$origins
            |Destinations=$destinations
            |Mode=$travelMode""".trimMargin())

        val distanceMatrixJson = queryGoogleMaps(travelMode, origins, destinations) // query matrix
        return buildDistanceMatrix(distanceMatrixJson, distanceType) // parse JSON
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

        // decode '|' and send request
        return uri.toString().replace(oldValue = "%7C", newValue = "|", ignoreCase = true)
                .URL.sendGet()
    }

    /**
     * A function to parse JSON string returned from Google Maps API
     * to a CostMatrix.
     * @param distanceType duration of distance.
     * @param response Google Maps API response.
     * @return CostMatrix of given JSON.
     */
    private fun buildDistanceMatrix(response : String, distanceType: DistanceType) : CostMatrix<String> {
        val parsedResponse = parseResponse(response)

        val keys = parsedResponse.originAddresses.product(parsedResponse.destinationAddresses)
        val values = parsedResponse.rows // get list of all rows
                .flatMap { it.elements } // get list of all elements

                // get duration/distance of each element
                .map { when(distanceType) {
                    DistanceType.Distance -> it.distance.value
                    DistanceType.Duration -> it.duration.value
                }}

        return CostMatrix(keys.zip(values).toMap())
    }
}