package com.example.mets634.deliveryman.controller.routeFinder.tspSolver

import com.example.mets634.deliveryman.controller.CostMatrix
import com.example.mets634.deliveryman.controller.Path

/**
 * A class to solve TSP problem.
 */
abstract class TspSolver<T>(protected val g : CostMatrix<T>, protected val root : T) {
    abstract val path : Path<T>
}