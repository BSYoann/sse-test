package com.bsyoann.seemvc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SeeMvcApplication

fun main(args: Array<String>) {
	runApplication<SeeMvcApplication>(*args)
}
