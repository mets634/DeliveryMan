package com.example.mets634.deliveryman.model.routeFinder.tspSolver

import com.example.mets634.deliveryman.model.CostMatrix
import com.example.mets634.deliveryman.model.Path
import com.example.mets634.deliveryman.model.Quad
import java.lang.Integer.MAX_VALUE


abstract class BranchAndBounder<T>(private val g : CostMatrix<T>, private val root : T) : TspSolver<T>() {
    private val emptyRes = Pair(emptyList<T>(), MAX_VALUE)

    abstract fun h(path : Path<T>) : Int

    private fun branchAndBound(
            path : Path<T> = emptyList(),
            cost : Int = 0,
            curr : T = root) : Pair<Path<T>, Int> {
        val newPath = path.plus(curr)

        // base case: score path
        if (newPath.containsAll(g.nodes))
            return Pair(newPath.plus(root), cost + g.getCost(curr, root))

        // expand to other nodes
       return (g.nodes - newPath) // for each unexplored node
                // map into quadruple of node, path, cost to node, and heuristic value
               .map {
                    val p = newPath.plus(it)
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

    override val path: Path<T> = branchAndBound().first
}