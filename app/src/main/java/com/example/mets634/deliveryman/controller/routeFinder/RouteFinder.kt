package com.example.mets634.deliveryman.controller.routeFinder

import com.example.mets634.deliveryman.controller.Path


enum class RouteFinderImp {
    BruteForce, BranchAndBound
}

/**
 * Abstract class to find best route for given list of addresses.
 * @param T Node type.
 */
interface RouteFinder<T> {

    /**
     * Function to calculate best route to take for given addresses.
     * @param nodes Nodes to find route for.
     */
    fun findRoute(vararg nodes : T, root : T) : Path<T>
}