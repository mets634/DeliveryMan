package com.example.mets634.deliveryman.controller.routeFinder.tspSolver.dynamicSolver.Bea


typealias Score = Int

abstract class Gene
typealias Bacteria = MutableList<Gene> // must be mutable for gene_transfer and mutate
typealias Population = List<Bacteria>