package com.tonypepe.mediumapi.api

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

val client by lazy {
    OkHttpClient.Builder()
        .build()
}

fun getUserData(token: String): MediumApiMe {
    val request = Request.Builder()
        .url("https://api.medium.com/v1/me")
        .get()
        .addHeader("Authorization", "Bearer $token")
        .build()
    val response = client.newCall(request).execute()
    if (response.code != 200 || response.body == null)
        throw RuntimeException(
            "Can not get User info. Maybe token is wrong.${response.body?.byteStream()?.reader()?.readLines()}"
        )

    val text = response.body!!.byteStream()
        .reader()
        .readText()
    return gson.fromJson(text, MediumApiMe::class.java)
}

fun newPost(token: String, mediumApiPost: MediumApiPost): String {
    val userID = getUserData(token).data.id

    val request = Request.Builder()
        .url("https://api.medium.com/v1/users/$userID/posts")
        .post(mediumApiPost.toJson().toRequestBody(contentType = "application/json".toMediaType()))
        .addHeader("Authorization", "Bearer $token")
        .build()
    val response = client.newCall(request).execute()

    if (response.code != 201 || response.body == null)
        throw RuntimeException("Something Wrong. ${response.body?.byteStream()?.reader()?.readText()}")

    val text = response.body!!.byteStream().reader().readText()
    println(text)

    var json = gson.fromJson(text, MediumNewPostResponse::class.java)

    return json.data.url
}
