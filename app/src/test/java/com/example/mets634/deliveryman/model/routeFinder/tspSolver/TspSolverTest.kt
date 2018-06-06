package com.example.mets634.deliveryman.model.routeFinder.tspSolver

import com.example.mets634.deliveryman.model.CostMatrix
import com.example.mets634.deliveryman.model.Path
import com.example.mets634.deliveryman.model.product
import org.junit.Test
import org.junit.Assert

abstract class TspSolverTest {
    /**
     * A function to get best path. Each implementation will
     * solve this in a different way.
     * @param matrix The CostMatrix of graph.
     * @param root The root node.
     */
    abstract fun getPath(matrix: CostMatrix<String>, root : String) : Path<String>

    @Test
    fun threeNodeTest() {
        // create cost matrix
        val nodes = listOf("a", "b", "c")
        val keys = nodes.product(nodes)

        val distances = listOf(0, 1, 5, 5, 0, 1, 1, 5, 0)
        val matrix = CostMatrix(keys.zip(distances).toMap())

        val res = listOf("a", "b", "c", "a")// result to compare to

        Assert.assertEquals(res, getPath(matrix, "a"))
    }

    @Test
    fun fourNodeTest() {
        // create cost matrix
        val nodes = listOf("a", "b", "c", "d")
        val keys = nodes.product(nodes)

        val distances = listOf(0, 1, 5, 5, 5, 0, 1, 5, 5, 5, 0, 1, 1, 5, 5, 0)
        val matrix = CostMatrix(keys.zip(distances).toMap())

        val res = listOf("a", "b", "c", "d", "a")// result to compare to

        Assert.assertEquals(res, getPath(matrix, "a"))
    }
}