package com.tonypepe.mediumapi

import com.google.gson.Gson

val gson = Gson()

data class MediumApiPost(
    val title: String,
    val content: String,
    val contentFormat: String,
//    val tags: List<String> = listOf(),
    val publishStatus: String = "draft",
//    val canonicalUrl: String = "",
) {
    fun toJson(): String {
        return gson.toJson(this)
    }
}
