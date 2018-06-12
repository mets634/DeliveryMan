package com.example.mets634.deliveryman.controller.routeFinder.tspSolver.staticSolver

import com.example.mets634.deliveryman.controller.Cost
import com.example.mets634.deliveryman.controller.CostMatrix
import com.example.mets634.deliveryman.controller.Path
import com.example.mets634.deliveryman.controller.Quad
import com.example.mets634.deliveryman.controller.routeFinder.tspSolver.TspSolver
import java.lang.Integer.MAX_VALUE


/**
 * A class to solve static TSP problem using the Branch-And-Bound algorithm.
 */
abstract class BranchAndBounder<T>(g : CostMatrix<T>, root : T) : TspSolver<T>(g, root) {
    private val emptyRes = Pair(emptyList<T>(), MAX_VALUE)

    /**
     * A function to bound the best case scenario for
     * a given graph and path. Used by branchAndBound.
     * @param path The current path chosen. Graph is already initialized.
     */
    protected abstract fun h(path : Path<T>) : Cost

    /**
     * A function to find best path using Branch-And-Bound algorithm.
     * @param path The current chosen path. Initially list of root.
     * @param cost The current cost of path. Initially 0.
     * @param curr The current node where path ends at. Initially root.
     */
    private fun branchAndBound(
            path : Path<T> = listOf(root),
            cost : Cost = 0,
            curr : T = root) : Pair<Path<T>, Cost> {
        // base case: score path
        if (path.containsAll(g.nodes))
            return Pair(path.plus(root), cost + g.getCost(curr, root))

        // expand to other nodes
       return (g.nodes - path) // for each unexplored node
                // map into quadruple of node, path, cost to node, and heuristic value
               .map {
                    val p = path.plus(it)
                    Quad(it, p, cost + g.getCost(curr, it), h(p))
                }
                // sort by cost to node + heuristic of node
                .sortedBy { it.third + it.fourth }

                // best is a pair of 1) path, 2) score of path
                .fold(emptyRes, { best, next ->
                    // prune next node
                    if (next.third + next.fourth >= best.second)
                        return best

                    // branch and choose better solution
                    val branch = branchAndBound(next.second, next.third, next.first)
                    return if (branch.second < best.second) branch else best
                })
    }

    override val path by lazy { branchAndBound().first }
}

/**
 * A class to implement heuristic for directed graph.
 */
class BranchAndBoundDirected<T>(g : CostMatrix<T>, root : T) : BranchAndBounder<T>(g, root) {
    override fun h(path: Path<T>): Cost = sum2MinVertices(g, path)
}

/**
 * A class to implement heuristic for undirected graph.
 */
class BranchAndBoundUndirected<T>(g : CostMatrix<T>, root : T) : BranchAndBounder<T>(g, root) {
    override fun h(path: Path<T>): Cost = sum2MinVertices(g, path) / 2
}

fun <T> sum2MinVertices(g : CostMatrix<T>, path : Path<T>) : Cost =
    (g.nodes - path) // sum upon all unused nodes
            .sumBy { node ->
                val smallest = g.vertices
                        .filter { it.first == node } // only use vertices from this node
                        .map { g.getCost(node, it.second) } // map vertices to costs
                        .sorted() // sort costs
                        .take(2) // select 2 smallest costs

                smallest[0] + smallest[1] // sum two cheapest vertices
            }