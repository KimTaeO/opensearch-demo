package com.example.opensearch.controller

import com.example.opensearch.dto.request.SaveOpensearchDocumentRequest
import com.example.opensearch.service.OpenSearchDocumentService
import org.opensearch.client.opensearch.core.IndexResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/opensearch/document")
class OpenSearchDocumentController(
    private val openSearchDocumentService: OpenSearchDocumentService
) {
    @PostMapping
    fun saveDocument(@RequestParam indexName: String, @RequestBody saveOpensearchDocumentRequest: SaveOpensearchDocumentRequest): ResponseEntity<IndexResponse> {
        val result = openSearchDocumentService.saveDocument(indexName, saveOpensearchDocumentRequest)
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }
}