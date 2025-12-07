package com.example.opensearch.config

import com.example.opensearch.config.properties.OpenSearchProperties
import org.apache.hc.client5.http.auth.AuthScope
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials
import org.apache.hc.client5.http.impl.async.HttpAsyncClientBuilder
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider
import org.apache.hc.core5.http.HttpHost
import org.apache.hc.core5.http2.HttpVersionPolicy
import org.opensearch.client.json.jackson.JacksonJsonpMapper
import org.opensearch.client.opensearch.OpenSearchClient
import org.opensearch.client.transport.httpclient5.ApacheHttpClient5TransportBuilder
import org.springframework.context.annotation.Configuration


@Configuration
class OpenSearchConfig(
    private val openSearchProperties: OpenSearchProperties
) {
    fun openSearchClient(): OpenSearchClient {
        val httpHost = HttpHost(openSearchProperties.protocol, openSearchProperties.host, openSearchProperties.port)

        val credentialsProvider = BasicCredentialsProvider()
        credentialsProvider.setCredentials(
            AuthScope(httpHost),
            UsernamePasswordCredentials("admin", "Asdf12345678!".toCharArray())
        )
        val opensearchTransport =
            ApacheHttpClient5TransportBuilder
                .builder(httpHost)
                .setHttpClientConfigCallback {
                    it.setDefaultCredentialsProvider(credentialsProvider)
                    it.setVersionPolicy(HttpVersionPolicy.FORCE_HTTP_2)
                }
                .setMapper(JacksonJsonpMapper())
                .build()

        return OpenSearchClient(opensearchTransport)
    }
}