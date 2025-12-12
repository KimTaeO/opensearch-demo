package com.example.opensearch.service

import com.example.opensearch.dto.request.SaveOpenSearchDocumentListRequest
import com.example.opensearch.dto.request.SaveOpensearchDocumentRequest
import com.example.opensearch.entity.Product
import org.opensearch.client.opensearch.OpenSearchClient
import org.opensearch.client.opensearch.core.IndexRequest
import org.opensearch.client.opensearch.core.IndexResponse
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class OpenSearchDocumentService(
    private val openSearchClient: OpenSearchClient
) {
    fun saveDocument(indexName: String, saveOpensearchDocumentRequest: SaveOpensearchDocumentRequest): IndexResponse {
        val product = Product(
            productName = saveOpensearchDocumentRequest.productName,
            ediCode = saveOpensearchDocumentRequest.ediCode,
            brandName = saveOpensearchDocumentRequest.brandName,
            brandId = saveOpensearchDocumentRequest.brandId
        )

        val req = IndexRequest.Builder<Product>()
            .index(indexName)
            .document(product)
            .build()

        val res = try {
            openSearchClient.index(req)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }

        return res
    }

    fun saveDocumentBatch(indexName: String, saveOpensearchDocumentListRequest: SaveOpenSearchDocumentListRequest): List<IndexResponse> {
        val res = saveOpensearchDocumentListRequest.list.map { saveOpensearchDocumentRequest ->
            val product = Product(
                productName = saveOpensearchDocumentRequest.productName,
                ediCode = saveOpensearchDocumentRequest.ediCode,
                brandName = saveOpensearchDocumentRequest.brandName,
                brandId = saveOpensearchDocumentRequest.brandId
            )

            val req = IndexRequest.Builder<Product>()
                .index(indexName)
                .document(product)
                .build()

            try {
                openSearchClient.index(req)
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        }

        return res
    }
}