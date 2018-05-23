package com.example.mets634.deliveryman.model.distanceFinder

import com.example.mets634.deliveryman.model.TravelMode
import com.example.mets634.deliveryman.model.distanceFinder.googleMaps.GoogleMapsDistanceFinder


/**
 * Type representing a distance matrix of a graph.
 * Maps a pair of locations to the corresponding cost.
 */
typealias DistanceMatrix = Map<Pair<String, String>, Int>

/**
 * Implementations of GoogleMapsDistanceFinder available.
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
     * GoogleMapsDistanceFinder factory.
     */
    companion object Factory {

        /**
         * Factory function. Return instance of wanted GoogleMapsDistanceFinder
         * @param distanceFinderImp Requested implementation of GoogleMapsDistanceFinder
         * @return Instance of GoogleMapsDistanceFinder requested.
         */
        fun getInstance(distanceFinderImp : DistanceFinderImp) : com.example.mets634.deliveryman.model.distanceFinder.DistanceFinder {
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
    fun calcDistanceMatrix(
            travelMode: TravelMode = TravelMode.Driving,
            origins: List<String>,
            destinations: List<String>
    ) : DistanceMatrix
}