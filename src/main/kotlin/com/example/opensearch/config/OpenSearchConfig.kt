package com.example.opensearch.config

import org.apache.hc.core5.http.HttpHost
import org.opensearch.client.json.jackson.JacksonJsonpMapper
import org.opensearch.client.opensearch.OpenSearchClient
import org.opensearch.client.transport.httpclient5.ApacheHttpClient5TransportBuilder
import org.springframework.context.annotation.Configuration

@Configuration
class OpenSearchConfig {
    fun openSearchClient(): OpenSearchClient {
        val httpHost = HttpHost("http", "localhost", 9200)

        val opensearchTransport =
            ApacheHttpClient5TransportBuilder
                .builder(httpHost)
                .setMapper(JacksonJsonpMapper())
                .build()

        return OpenSearchClient(opensearchTransport)
    }
}