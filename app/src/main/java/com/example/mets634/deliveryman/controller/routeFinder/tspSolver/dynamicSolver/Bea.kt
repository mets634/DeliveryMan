package com.example.mets634.deliveryman.controller.routeFinder.tspSolver.dynamicSolver

import com.example.mets634.deliveryman.controller.selectRandom
import com.example.mets634.deliveryman.controller.selectRandomIndex

typealias Score = Int

abstract class Gene
typealias Bacteria = MutableList<Gene> // must be mutable for gene_transfer and mutate
typealias Population = List<Bacteria>

/**
 * A class to run the Bacterial-Evolution-Algorithm.
 *
 * @see "Solution for Modified Traveling Salesman Problem
 * with Variable Cost Matrix Using Bacterial
 * Evolutionary Algorithm"
 * by P. Földesi, J. Botzheim

 */
abstract class GeneralBea(private val numOfGenerations : Int = 10,
          private val numOfClones : Int = 2,
          private val numOfBacteria : Int = 50,
          private val numOfTransfers : Int = 50) {

    /**
     * A function to calculate a score for
     * this bacteria.
     * @return Score of this bacteria.
     */
    abstract fun score(bacteria: Bacteria) : Score

    fun mutate(population : Population) {
        val clones = population.map { b -> MutableList(this.numOfClones) { b.toList() } }.flatten()
    }

    /**
     * A function to transfer genes from the better half
     * of the population to the lesser half of the population.
     *
     * Which gene to transfer is randomly chosen, as well as
     * which bacteria to transfer from and to.
     *
     * @param population The population of bacteria.
     */
    fun transferGenes(population : Population) {
        // divide population into inferior and superior halves

        val sortedPopulation = population
                .sortedBy { score(it) }

        val inferiorHalf = sortedPopulation.take(population.size / 2)
        val superiorHalf = sortedPopulation.takeLast(population.size / 2)

        // repeatedly transfer a random segment from the
        // superior half to the inferior half
        repeat(numOfTransfers) {
            // select random bacteria from each population
            val superior = superiorHalf.selectRandom()
            val inferior = inferiorHalf.selectRandom()

            // transfer random segment from superior to inferior
            val geneIndex = superior.selectRandomIndex()
            inferior[geneIndex] = superior[geneIndex]
        }
    }
}