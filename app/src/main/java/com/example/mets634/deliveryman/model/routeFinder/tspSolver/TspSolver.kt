package com.example.mets634.deliveryman.model.routeFinder.tspSolver

import com.example.mets634.deliveryman.model.CostMatrix
import com.example.mets634.deliveryman.model.Path
import com.example.mets634.deliveryman.model.routeFinder.RouteFinder

/**
 * A class to solve TSP problem.
 */
abstract class TspSolver<T>(protected val g : CostMatrix<T>, protected val root : T) {
    abstract val path : Path<T>
}