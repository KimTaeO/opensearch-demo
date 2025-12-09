package com.example.opensearch.dto.request

import java.math.BigInteger

data class SaveOpensearchDocumentRequest(
    val productName: String,
    val ediCode: String,
    val brandName: String,
    val brandId: BigInteger
)
