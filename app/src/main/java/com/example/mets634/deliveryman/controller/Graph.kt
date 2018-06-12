package com.example.mets634.deliveryman.controller

typealias Cost = Int

typealias Vertex<T> = Pair<T, T>

typealias Path<T> = List<T>

data class CostMatrix<T>(val table : Map<Vertex<T>, Cost>) {
    init { // make sure table is a squared one
        val rows = table.keys.map { it.first }
        val columns = table.keys.map { it.second }

        if (rows.toSet() != columns.toSet())
            throw ExceptionInInitializerError("CostMatrix must be a square table")
    }

    val nodes = table.keys.map { it.first }.toSet()
    val vertices = table.keys

    /**
     * A function to get the cost between two nodes.
     * @param node1 First node.
     * @param node2 Second node.
     * @return The cost of moving in between nodes.
     * @throws NoSuchElementException If verice doesn't exist. This shouldn't happen.
     */
    fun getCost(node1 : T, node2 : T) : Cost = table.getValue(Pair(node1, node2))
}