package com.example.mets634.deliveryman.controller

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

/**
 * A function to randomly select an item from a collection.
 * @return A random item.
 */
fun <T> Collection<T>.selectRandom() : T = this.shuffled()[0]

/**
 * A function to randomly select an index from a list.
 * @return A random index.
 */
fun <T> Collection<T>.selectRandomIndex() : Int = this.indexOf(selectRandom())