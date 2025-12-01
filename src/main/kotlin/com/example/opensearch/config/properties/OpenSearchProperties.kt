package com.example.opensearch.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "opensearch")
data class OpenSearchProperties(
    val protocol: String,
    val host: String,
    val port: Int
)