package com.example.mets634.deliveryman.controller.distanceFinder.googleMaps

import org.junit.Test
import org.junit.Assert.assertEquals

class JsonDataTest {

    @Test
    fun parseResponseSingleElement() {
        val json = """{
   "destination_addresses" : [ "Bobcaygeon, Kawartha Lakes, ON, Canada" ],
   "origin_addresses" : [ "24 Sussex Dr, Ottawa, ON K1M 1M4, Canada" ],
   "rows" : [
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
            }
         ]
      }
   ],
   "status" : "OK"
}"""

        val origin = listOf("24 Sussex Dr, Ottawa, ON K1M 1M4, Canada")
        val destin = listOf("Bobcaygeon, Kawartha Lakes, ON, Canada")
        val status = "OK"
        val rows = listOf(
                Row(
                        elements = listOf(
                                Element(
                                        distance = Record("317 km", 316539),
                                        duration = Record("3 hours 49 mins", 13760),
                                        status = "OK"
                                )
                        )
                )
        )

        val realVal = GoogleMapsResponse(origin, destin, rows, status)
        assertEquals(realVal, com.example.mets634.deliveryman.controller.distanceFinder.googleMaps.parseResponse(json))
    }

    @Test
    fun parseResponseFourElements() {
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
}
"""

        val origin = listOf("Bobcaygeon, Kawartha Lakes, ON, Canada",
                "24 Sussex Dr, Ottawa, ON K1M 1M4, Canada")
        val destin = listOf("Bobcaygeon, Kawartha Lakes, ON, Canada",
                "24 Sussex Dr, Ottawa, ON K1M 1M4, Canada")
        val status = "OK"
        val rows = listOf(
                Row(
                        elements = listOf(
                                Element(
                                      distance = Record("1 m", 0),
                                      duration = Record("1 min", 0),
                                      status = "OK"
                                ),
                                Element(
                                        distance = Record("317 km", 317220),
                                        duration = Record("3 hours 51 mins", 13851),
                                        status = "OK"
                                )
                        )
                ),
                Row(
                        elements = listOf(
                                Element(
                                        distance = Record("317 km", 316539),
                                        duration = Record("3 hours 49 mins", 13760),
                                        status = "OK"
                                ),
                                Element(
                                        distance = Record("1 m", 0),
                                        duration = Record("1 min", 0),
                                        status = "OK"
                                )
                        )
                )
        )

        val realVal = GoogleMapsResponse(origin, destin, rows, status)
        assertEquals(realVal, com.example.mets634.deliveryman.controller.distanceFinder.googleMaps.parseResponse(json))
    }
}