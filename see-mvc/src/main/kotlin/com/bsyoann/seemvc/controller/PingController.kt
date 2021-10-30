package com.bsyoann.seemvc.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.time.LocalTime
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


@RequestMapping("api/v1/ping")
@RestController
class PingController {

    @GetMapping()
    fun ping(): ResponseEntity<String> {
        return ResponseEntity.ok("Ping OK");
    }

    @GetMapping("/stream")
    fun streamPing(): SseEmitter {
        val sseEmitter = SseEmitter();
        Executors.newSingleThreadExecutor().execute {
            var i = 0
            while (true) {
                val event = SseEmitter.event()
                        .data(LocalTime.now().toString())
                        .id(i.toString())
                        .name("ping")
                        .reconnectTime(TimeUnit.SECONDS.toMillis(10))
                sseEmitter.send(event)
                Thread.sleep(1000)
                i++
            }
        }
        return sseEmitter;
    }
}