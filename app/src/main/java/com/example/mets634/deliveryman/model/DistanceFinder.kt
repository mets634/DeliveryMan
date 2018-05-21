package com.example.mets634.deliveryman.model

interface DistanceFinder {
    fun calcDistance(address1 : String, address2 : String) : String
}