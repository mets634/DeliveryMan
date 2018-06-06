package com.example.mets634.deliveryman.model.routeFinder.tspSolver

import com.example.mets634.deliveryman.model.Path

abstract class TspSolver<out T> {
    abstract val path : Path<T>
}