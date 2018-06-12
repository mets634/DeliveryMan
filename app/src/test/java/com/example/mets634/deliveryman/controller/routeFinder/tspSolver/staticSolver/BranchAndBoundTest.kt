package com.example.mets634.deliveryman.controller.routeFinder.tspSolver.staticSolver

import com.example.mets634.deliveryman.controller.CostMatrix
import com.example.mets634.deliveryman.controller.Path


class BranchAndBoundDirectedTest : TspSolverDirectedTest() {
    override fun <T> getPath(matrix: CostMatrix<T>, root: T): Path<T> =
            BranchAndBoundDirected(matrix, root).path
}

class BranchAndBoundUndirectedTest : TspSolverUndirectedTest() {
    override fun <T> getPath(matrix: CostMatrix<T>, root: T): Path<T> =
            BranchAndBoundUndirected(matrix, root).path
}