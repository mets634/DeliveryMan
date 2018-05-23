package com.example.mets634.deliveryman.model.distanceFinder.googleMaps

import com.example.mets634.deliveryman.model.TravelMode
import junit.framework.Assert
import org.junit.Test
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever



class GoogleMapsDistanceFinderTest {

    @Test
    fun calcDistanceMatrix() {
        /*
        NOTE: mapping may change over time!
        Last date updated: 23/5/18
         */

        // setup value to compare result to

        val origins = listOf("Bobcaygeon, Kawartha Lakes, ON, Canada", "Bobcaygeon, Kawartha Lakes, ON, Canada",
                "24 Sussex Dr, Ottawa, ON K1M 1M4, Canada")
        val destins = listOf("24 Sussex Dr, Ottawa, ON K1M 1M4, Canada",
                "Bobcaygeon, Kawartha Lakes, ON, Canada", "Bobcaygeon, Kawartha Lakes, ON, Canada")

        val realVal = mapOf(
                Pair("Bobcaygeon, Kawartha Lakes, ON, Canada", "Bobcaygeon, Kawartha Lakes, ON, Canada")
                        to 0,
                Pair("Bobcaygeon, Kawartha Lakes, ON, Canada", "24 Sussex Dr, Ottawa, ON K1M 1M4, Canada")
                        to 13851,
                Pair("24 Sussex Dr, Ottawa, ON K1M 1M4, Canada", "Bobcaygeon, Kawartha Lakes, ON, Canada")
                        to 13760,
                Pair("24 Sussex Dr, Ottawa, ON K1M 1M4, Canada", "24 Sussex Dr, Ottawa, ON K1M 1M4, Canada")
                        to 0
        )

        // setup of mocking queryGoogleMaps

        val json = """
            {
   "destination_addresses" : [
      "Bobcaygeon, Kawartha Lakes, ON, Canada",
      "24 Sussex Dr, Ottawa, ON K1M 1M4, Canada"
   ],
   "origin_addresses" : [
      "Bobcaygeon, Kawartha Lakes, ON, Canada",
      "24 Sussex Dr, Ottawa, ON K1M 1M4, Canada"
   ],
   "rows" : [
      {
         "elements" : [
            {
               "distance" : {
                  "text" : "1 m",
                  "value" : 0
               },
               "duration" : {
                  "text" : "1 min",
                  "value" : 0
               },
               "status" : "OK"
            },
            {
               "distance" : {
                  "text" : "317 km",
                  "value" : 317220
               },
               "duration" : {
                  "text" : "3 hours 51 mins",
                  "value" : 13851
               },
               "status" : "OK"
            }
         ]
      },
      {
         "elements" : [
            {
               "distance" : {
                  "text" : "317 km",
                  "value" : 316539
               },
               "duration" : {
                  "text" : "3 hours 49 mins",
                  "value" : 13760
               },
               "status" : "OK"
            },
            {
               "distance" : {
                  "text" : "1 m",
                  "value" : 0
               },
               "duration" : {
                  "text" : "1 min",
                  "value" : 0
               },
               "status" : "OK"
            }
         ]
      }
   ],
   "status" : "OK"
}"""

        val mockedMaps : GoogleMapsDistanceFinder = mock()
        whenever(mockedMaps.queryGoogleMaps(TravelMode.Walking, origins, destins)).thenReturn(json)

        Assert.assertEquals(realVal, mockedMaps.calcDistanceMatrix(TravelMode.Walking, origins, destins))
    }
}