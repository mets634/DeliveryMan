package com.example.mets634.deliveryman.model.routeFinder

import com.example.mets634.deliveryman.model.routeFinder.tspSolver.BruteForcer


enum class RouteFinderImp {
    BruteForce, BranchAndBound
}

/**
 * Abstract class to find best route for given list of addresses.
 * @param T Node type.
 */
interface RouteFinder<in T> {

    /**
     * RouteFinder factory.
     */
    companion object Factory {

        /**
         * Factory function. Return instance of wanted RouteFinder.
         * @param routeFinderImp Requested implementation of RouteFinder
         * @return Instance of RouteFinder requested.
         */
        fun <T> getInstance(routeFinderImp : RouteFinderImp) : RouteFinder<T> {
            return when (routeFinderImp) {
                else -> throw Exception("Unidentified RouteFinderImp")
            }
        }
    }

    /**
     * Function to calculate best route to take for given addresses.
     * @param nodes Nodes to find route for.
     */
    fun findRoute(vararg nodes : T) : List<String>}