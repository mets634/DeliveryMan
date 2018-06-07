package com.example.mets634.deliveryman.model.routeFinder.tspSolver.staticSolver

import com.example.mets634.deliveryman.model.CostMatrix
import com.example.mets634.deliveryman.model.Path


class BruteForceTest : TspSolverDirectedTest() {
    override fun <T> getPath(matrix: CostMatrix<T>, root: T): Path<T> =
            BruteForcer(matrix, root).path
}