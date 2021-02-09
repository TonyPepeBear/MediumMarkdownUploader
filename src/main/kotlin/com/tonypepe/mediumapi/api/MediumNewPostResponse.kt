package com.tonypepe.mediumapi.api

data class MediumNewPostResponse(
    val `data`: MediumNewPostResponseData
)

data class MediumNewPostResponseData(
    val authorId: String,
    val canonicalUrl: String,
    val id: String,
    val license: String,
    val licenseUrl: String,
    val publishStatus: String,
    val tags: List<Any>,
    val title: String,
    val url: String
)
