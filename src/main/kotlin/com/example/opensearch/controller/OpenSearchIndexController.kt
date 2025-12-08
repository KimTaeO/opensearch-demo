package com.example.opensearch.controller

import com.example.opensearch.service.OpenSearchIndexService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/opensearch")
class OpenSearchIndexController(
    private val openSearchIndexService: OpenSearchIndexService
) {
    @PostMapping
    fun createIndex(): ResponseEntity<Unit> {
        openSearchIndexService.createIndex()
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @GetMapping
    fun isIndexExist(@RequestParam indexName: String): ResponseEntity<Boolean> {
        val result = openSearchIndexService.isIndexExist(indexName)
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }

    @DeleteMapping
    fun deleteIndex(@RequestParam indexName: String): ResponseEntity<Boolean> {
        openSearchIndexService.deleteIndex(indexName)
        return ResponseEntity.status(HttpStatus.OK).build()
    }
}