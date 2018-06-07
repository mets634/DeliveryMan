package com.example.mets634.deliveryman.model.routeFinder.tspSolver.staticSolver

import com.example.mets634.deliveryman.model.Cost
import com.example.mets634.deliveryman.model.CostMatrix
import com.example.mets634.deliveryman.model.Path
import com.example.mets634.deliveryman.model.routeFinder.tspSolver.TspSolver


/**
 * A class to brute force the solution to static TSP problem.
 */
class BruteForcer<T>(g : CostMatrix<T>, root : T) : TspSolver<T>(g, root) {

    /**
     * A function to brute force the result for TSP.
     * @param path The current path created.
     * @param cost The current cost of path.
     * @param curr The current node expanding.
     * @return Pair of final path and the cost of it.
     */
    private fun bruteForce(
            path : Path<T> = emptyList(),
            cost : Cost = 0,
            curr : T = root) : Pair<Path<T>, Cost> {
        // try each non-used node to find best path
        val newPath = path.plus(curr)
        val bestPath = (g.nodes - newPath) // for each unexplored node
                // find it's best solution down this path
                .map { bruteForce(
                        newPath,
                        cost + g.getCost(curr, it),
                        it
                ) }
                .minBy { it.second } // find lowest cost

        /*2 cases:
        * 1) bestPath is not null -> continue down tree.
        * 2) bestPath is null -> base case, no more to explore.*/
        return bestPath ?: Pair(newPath.plus(root), cost + g.getCost(curr, root))
    }

    // WARNING: May take a lot of time to run!!!
    override val path by lazy { bruteForce().first }
}

