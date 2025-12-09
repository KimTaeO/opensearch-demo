package com.example.opensearch.entity

import java.math.BigInteger

data class Product(
    val productName: String,
    val ediCode: String,
    val brandName: String,
    val brandId: BigInteger
)