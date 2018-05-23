package com.example.mets634.deliveryman.model.distanceFinder

import com.example.mets634.deliveryman.model.TravelMode


/**
 * Implementations of DistanceFinder available.
 */
enum class DistanceFinderImp {
    GoogleMaps
}

/**
 * Interface for finding distances between locations.
 * NOTE: Distance may be measured in time rather than
 * by physical distance.
 */
interface DistanceFinder {

    /**
     * DistanceFinder factory.
     */
    companion object Factory {

        /**
         * Factory function. Return instance of wanted DistanceFinder
         * @param distanceFinderImp Requested implementation of DistanceFinder
         * @return Instance of DistanceFinder requested.
         */
        fun getInstance(distanceFinderImp : DistanceFinderImp) : DistanceFinder {
            when (distanceFinderImp) {
                DistanceFinderImp.GoogleMaps -> return GoogleMapsDistanceFinder
                else -> throw Exception("Unidentified DistanceFinderImp")
            }
        }
    }

    /**
     * Function to return the distance between addresses.
     * @param travelMode Instance of TravelMode.
     * @param addresses Pairs of addresses to find distance to each other.
     * @return A map from pairs of locations to the distance from each other.
     */
    fun calcDistance(
            travelMode: TravelMode = TravelMode.Driving,
            vararg addresses : Pair<String, String>
    ) : DistanceMatrix
}