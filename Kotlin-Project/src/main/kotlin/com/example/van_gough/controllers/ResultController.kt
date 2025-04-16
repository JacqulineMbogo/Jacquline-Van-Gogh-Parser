package com.example.van_gough

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ResultController(private val parser: ResultParser) {

    @GetMapping
    fun getResults(@RequestParam(defaultValue = "../files/van-gogh-paintings.html") path: String): List<ResultItem> {
        return parser.parseResult(path)
    }
}
