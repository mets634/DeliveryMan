package com.example.mets634.deliveryman.model

abstract class RouteFinder {
    abstract fun findRoute(addresses : List<String>) : List<String>
}