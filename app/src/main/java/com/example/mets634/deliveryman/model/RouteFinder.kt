package com.example.mets634.deliveryman.model


/**
 * Abstract class to find best route for given list of addresses.
 */
abstract class RouteFinder {

    /**
     * Abstract function to calculate best route to take for given addresses.
     * @param addresses Addresses to find route for.
     */
    abstract fun findRoute(vararg addresses : String) : List<String>
}