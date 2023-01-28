package com.pullout

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableBatchProcessing
class PulloutBatchApplication

fun main(args: Array<String>) {
    runApplication<PulloutBatchApplication>(*args)
}