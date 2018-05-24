package com.example.mets634.deliveryman.model

typealias Vertex<T> = Pair<T, T>

data class Graph<T> (val nodes : List<T>, val vertices : List<Vertex<T>>)

typealias DistanceMatrix<T> = Map<Vertex<T>, Int>