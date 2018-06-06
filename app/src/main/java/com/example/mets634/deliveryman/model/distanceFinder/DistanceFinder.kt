package com.example.mets634.deliveryman.model.distanceFinder

import com.example.mets634.deliveryman.model.CostMatrix
import com.example.mets634.deliveryman.model.distanceFinder.googleMaps.GoogleMapsDistanceFinder


/**
 * Implementations of GoogleMapsDistanceFinder available.
 */
enum class DistanceFinderImp {
    GoogleMaps
}

/**
 * Interface for finding distances between locations.
 * NOTE: Distance is measured in time rather than euclidean
 * distance.
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
        fun getInstance(distanceFinderImp : DistanceFinderImp) : com.example.mets634.deliveryman.model.distanceFinder.DistanceFinder {
            when (distanceFinderImp) {
                DistanceFinderImp.GoogleMaps -> return GoogleMapsDistanceFinder
                else -> throw Exception("Unidentified DistanceFinderImp")
            }
        }
    }

    /**
     * Function to return the distance between addresses.
     * @param distanceType distance or duration.
     * @param travelMode Instance of TravelMode.
     * @param origins List of origin addresses
     * @param destinations List of destination addresses.
     * @return A map from pairs of locations to the distance from each other.
     */
    fun calcDistanceMatrix(
            distanceType: DistanceType,
            travelMode: TravelMode = TravelMode.Driving,
            origins: List<String>,
            destinations: List<String>
    ) : CostMatrix<String>

}