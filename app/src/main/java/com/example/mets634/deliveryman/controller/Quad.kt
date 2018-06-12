package com.example.mets634.deliveryman.controller

data class Quad<out T1, out T2, out T3, out T4>(
        val first : T1,
        val second : T2,
        val third : T3,
        val fourth : T4
)