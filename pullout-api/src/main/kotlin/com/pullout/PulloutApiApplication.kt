package com.pullout

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PulloutApiApplication

fun main(args: Array<String>) {
    runApplication<PulloutApiApplication>(*args)
}