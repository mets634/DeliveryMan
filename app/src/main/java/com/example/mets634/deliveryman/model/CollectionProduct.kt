package com.example.mets634.deliveryman.model

/**
 * Extension method for Collection to get Cartesian-product
 * from two collections.
 * @param T This collection's type.
 * @param K Other's collection type.
 * @param other Other collection
 * @return Collection containing product of two collections.
 */
fun <T, K> Collection<T>.product(other : Collection<K>) =
        this.map { outer -> other.map { inner -> Pair(outer, inner) } }.flatten()
