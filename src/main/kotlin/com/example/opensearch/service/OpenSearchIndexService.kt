package com.example.opensearch.service

import org.opensearch.client.opensearch.OpenSearchClient
import org.opensearch.client.opensearch._types.mapping.IntegerNumberProperty
import org.opensearch.client.opensearch._types.mapping.KeywordProperty
import org.opensearch.client.opensearch._types.mapping.Property
import org.opensearch.client.opensearch._types.mapping.TypeMapping
import org.opensearch.client.opensearch.indices.CreateIndexRequest
import org.opensearch.client.opensearch.indices.DeleteIndexRequest
import org.opensearch.client.opensearch.indices.ExistsRequest
import org.opensearch.client.opensearch.indices.IndexSettings
import org.springframework.stereotype.Service

@Service
class OpenSearchIndexService(
    private val openSearchClient: OpenSearchClient
) {
    fun createIndex() {
        val indexName = "index"

        val indexSetting = IndexSettings.Builder()
            .numberOfShards(1)
            .numberOfReplicas(1)
            .build()

        val typeMapping = TypeMapping.Builder()
            .properties("productName",  Property.Builder().keyword(KeywordProperty.Builder().build()).build())
            .properties("ediCode",      Property.Builder().keyword(KeywordProperty.Builder().ignoreAbove(20).build()).build())
            .properties("brandName",  Property.Builder().keyword(KeywordProperty.Builder().build()).build())
            .properties("brandId",      Property.Builder().integer(IntegerNumberProperty.Builder().build()).build())
            .build()


        val req = CreateIndexRequest
            .Builder()
            .index(indexName)
            .settings(indexSetting)
            .mappings(typeMapping)
            .build()

        openSearchClient.indices().create(req)
    }

    fun isIndexExist(indexName: String): Boolean {
        val req = ExistsRequest
            .Builder()
            .index(indexName)
            .build()

        val res = openSearchClient.indices().exists(req)
        return res.value()
    }

    fun deleteIndex(indexName: String) {
        val req = DeleteIndexRequest
            .Builder()
            .index(indexName).build()

        openSearchClient.indices().delete(req)
    }
}