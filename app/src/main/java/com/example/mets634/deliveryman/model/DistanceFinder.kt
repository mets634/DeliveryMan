package com.example.mets634.deliveryman.model


/**
 * USAGE: matrix["origin string"]["destination string"].
 */
typealias DistanceMatrix = Map<String, Map<String, Distance>>

/**
 * Represents the distance between two locations.
 */
typealias Distance = Int

/**
 * Interface for finding distances between locations.
 * NOTE: Distance may be measured in time rather than
 * by physical distance.
 */
interface DistanceFinder {

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