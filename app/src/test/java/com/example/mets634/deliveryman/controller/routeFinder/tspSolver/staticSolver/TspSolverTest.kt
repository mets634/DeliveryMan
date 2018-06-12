package com.example.mets634.deliveryman.controller.routeFinder.tspSolver.staticSolver

import com.example.mets634.deliveryman.controller.CostMatrix
import com.example.mets634.deliveryman.controller.Path
import com.example.mets634.deliveryman.controller.product
import org.junit.Test
import org.junit.Assert

abstract class TspSolverUndirectedTest {
    /**
     * A function to get best path. Each implementation will
     * solve this in a different way.
     * @param matrix The CostMatrix of graph.
     * @param root The root node.
     */
    abstract fun <T> getPath(matrix: CostMatrix<T>, root : T) : Path<T>

    @Test
    fun threeNodeTest() {
        // create cost matrix
        val nodes = listOf("a", "b", "c")
        val keys = nodes.product(nodes)

        val distances = listOf(
                0, 1, 5,
                5, 0, 1,
                1, 5, 0)
        val matrix = CostMatrix(keys.zip(distances).toMap())

        val res = listOf("a", "b", "c", "a")// result to compare to

        Assert.assertEquals(res, getPath(matrix, "a"))
    }

    @Test
    fun fourNodeTest() {
        // create cost matrix
        val nodes = listOf("a", "b", "c", "d")
        val keys = nodes.product(nodes)

        val distances = listOf(
                0, 1, 5, 5,
                5, 0, 1, 5,
                5, 5, 0, 1,
                1, 5, 5, 0)
        val matrix = CostMatrix(keys.zip(distances).toMap())

        val res = listOf("a", "b", "c", "d", "a")// result to compare to

        Assert.assertEquals(res, getPath(matrix, "a"))
    }
}

abstract class TspSolverDirectedTest : TspSolverUndirectedTest() {
    /**
     * Branch and Bound algorithm (both heuristics) should do well here.
     * Should adjust test size so that we can see the performance differences.
     * 11 is Usually enough.
     */
    @Test
    fun branchAndBoundPerformanceTest() {
        branchAndBoundPerformanceTest(7)
    }

    private fun branchAndBoundPerformanceTest(n : Int) {
        val INF = 100000

        // create cost matrix
        val nodes = (1 .. n).toList()
        val keys = nodes.product(nodes)

        val distances =
                keys
                        .map {
                            if (it.first == it.second) // on horizontal
                                0
                            else if ((it.first + 1) % n == 1) // connected to
                                1
                            else // not connected to
                                INF
                        }

        val matrix = CostMatrix(keys.zip(distances).toMap())

        val res = nodes.plusElement(1) // result to compare to

        Assert.assertEquals(res, getPath(matrix, 1))
    }
}