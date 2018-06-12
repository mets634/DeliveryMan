package com.example.mets634.deliveryman.controller.routeFinder.tspSolver.staticSolver

import com.example.mets634.deliveryman.controller.CostMatrix
import com.example.mets634.deliveryman.controller.Path


class BruteForceTest : TspSolverDirectedTest() {
    override fun <T> getPath(matrix: CostMatrix<T>, root: T): Path<T> =
            BruteForcer(matrix, root).path
}