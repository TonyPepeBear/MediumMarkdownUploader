package com.tonypepe.mediumapi.api

data class MediumApiMe(
    val `data`: MediumMeData
)

data class MediumMeData(
    val id: String,
    val imageUrl: String,
    val name: String,
    val url: String,
    val username: String
)