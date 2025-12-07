package com.example.opensearch.controller

import com.example.opensearch.service.OpenSearchService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/opensearch")
class OpenSearchController(
    private val openSearchService: OpenSearchService
) {
    @PostMapping
    fun createIndex(): ResponseEntity<Unit> {
        openSearchService.createIndex()
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @GetMapping
    fun isIndexExist(@RequestParam indexName: String): ResponseEntity<Boolean> {
        val result = openSearchService.isIndexExist(indexName)
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }
}