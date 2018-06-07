package com.example.mets634.deliveryman.model.routeFinder.tspSolver.staticSolver

import com.example.mets634.deliveryman.model.Cost
import com.example.mets634.deliveryman.model.CostMatrix
import com.example.mets634.deliveryman.model.Path
import com.example.mets634.deliveryman.model.routeFinder.tspSolver.TspSolver
import org.chocosolver.solver.Model

/**
 * A class to solve TSP using Mixed Integer Linear Programming.
 */
class MixedIntegerSolver<T>(g : CostMatrix<T>, root : T) : TspSolver<T>(g, root) {

    private fun solveMixedInteger(
            path : Path<T> = emptyList(),
            cost : Cost = 0,
            curr : T = root) : Pair<Path<T>, Cost> {


        throw NotImplementedError("Not finished implementing algorithm")
    }

    override val path: Path<T> by lazy { solveMixedInteger().first }
}
