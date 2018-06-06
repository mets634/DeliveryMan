package com.example.mets634.deliveryman.model.distanceFinder.googleMaps

import android.support.test.filters.SmallTest
import com.example.mets634.deliveryman.model.distanceFinder.DistanceType
import com.example.mets634.deliveryman.model.distanceFinder.TravelMode
import junit.framework.Assert
import org.junit.Test


class GoogleMapsDistanceFinderTest {
    companion object {

        /*NOTE: mapping may change over time!
        Last date updated: 23/5/18 */
        val cmpMatrixCmp1 = mapOf(
                Pair("Bobcaygeon, Kawartha Lakes, ON, Canada", "Bobcaygeon, Kawartha Lakes, ON, Canada")
                        to 0,
                Pair("Bobcaygeon, Kawartha Lakes, ON, Canada", "24 Sussex Dr, Ottawa, ON K1M 1M4, Canada")
                        to 13851,
                Pair("24 Sussex Dr, Ottawa, ON K1M 1M4, Canada", "Bobcaygeon, Kawartha Lakes, ON, Canada")
                        to 13760,
                Pair("24 Sussex Dr, Ottawa, ON K1M 1M4, Canada", "24 Sussex Dr, Ottawa, ON K1M 1M4, Canada")
                        to 0
        )

        val origins1 = listOf("Bobcaygeon, Kawartha Lakes, ON, Canada",
                "24 Sussex Dr, Ottawa, ON K1M 1M4, Canada")

        val destinations1 = listOf("24 Sussex Dr, Ottawa, ON K1M 1M4, Canada",
                "Bobcaygeon, Kawartha Lakes, ON, Canada")
    }


    @Test
    @SmallTest
    fun calcDistanceMatrix() {
        Assert.assertEquals(cmpMatrixCmp1,
                GoogleMapsDistanceFinder.calcDistanceMatrix(DistanceType.Distance, TravelMode.Walking, origins1, destinations1))
    }
}