package com.fitness.stronger

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform