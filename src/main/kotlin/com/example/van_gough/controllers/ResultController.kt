package com.example.van_gough

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ResultController(private val parser: ResultParser) {

    @GetMapping
    fun getResults(): List<ResultItem> {
        val path = "files/van-gogh-paintings.html"
        return parser.parseResult(path)
    }
}
