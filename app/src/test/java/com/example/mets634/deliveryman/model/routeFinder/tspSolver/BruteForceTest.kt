package com.example.mets634.deliveryman.model.routeFinder.tspSolver

import com.example.mets634.deliveryman.model.CostMatrix
import com.example.mets634.deliveryman.model.Path


class BruteForceTest : TspSolverTest() {
    override fun getPath(matrix: CostMatrix<String>, root: String): Path<String> =
            BruteForcer(matrix, root).path
}