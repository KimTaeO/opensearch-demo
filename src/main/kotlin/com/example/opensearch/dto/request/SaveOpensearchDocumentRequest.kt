package com.example.opensearch.dto.request

import java.math.BigInteger

data class SaveOpensearchDocumentRequest(
    val productName: String,
    val ediCode: String,
    val atcCode: String,
    val brandName: String,
    val brandId: BigInteger,
    val productId: Int
)
