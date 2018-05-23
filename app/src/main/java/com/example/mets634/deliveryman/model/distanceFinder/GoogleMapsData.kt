package com.example.mets634.deliveryman.model.distanceFinder

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * File contains data classes corresponding to the resulting JSON sent
 * back from the Google API.
 */

// JSON key values
const val ORIGINS_FIELD = "origin_addresses"
const val DESTINATIONS_FIELD = "destination_addresses"
const val ROWS_FIELD = "rows"
const val ELEMENTS_FIELD = "element"
const val DURATION_FIELD = "duration"
const val DISTANCE_FIELD = "distance"
const val VALUE_FIELD = "value"
const val TEXT_FIELD = "text"
const val STATUS_FIELD = "status"

/*data classes corresponding to JSON structure*/

data class Record(
        @SerializedName(TEXT_FIELD) val text: String,
        @SerializedName(VALUE_FIELD) val value: Int
)

data class Element(
        @SerializedName(DISTANCE_FIELD) val distance: Record,
        @SerializedName(DURATION_FIELD) val duration: Record,
        @SerializedName(STATUS_FIELD) val status: String
)

data class Row(
        @SerializedName(ELEMENTS_FIELD) val elements: List<Element>
)

data class GoogleMapsResponse(
        @SerializedName(ORIGINS_FIELD) val originAddresses: List<String>,
        @SerializedName(DESTINATIONS_FIELD) val destinationAddresses: List<String>,
        @SerializedName(ROWS_FIELD) val rows: List<Row>,
        @SerializedName(STATUS_FIELD) val status: String
)


/**
 * Parse JSON to response.
 * @param response The JSON to parse.
 * @return Instance of GoogleMapsResponse with data in it.
 */
fun parseGoogleResponse(response : String) = Gson().fromJson(response, GoogleMapsResponse::class.java)