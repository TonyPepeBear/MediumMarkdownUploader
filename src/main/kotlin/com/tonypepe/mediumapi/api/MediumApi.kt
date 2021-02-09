package com.tonypepe.mediumapi.api

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.*

val client by lazy {
    OkHttpClient.Builder()
        .build()
}

fun getUserData(token: String): MediumApiMe? {
    val request = Request.Builder()
        .url("https://api.medium.com/v1/me")
        .get()
        .addHeader("Authorization", "Bearer $token")
        .build()
    val response = client.newCall(request).execute()
    response.body?.run {
        val scanner = Scanner(byteStream())
        val sb = StringBuilder()
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine())
        }
        println(sb)
        return gson.fromJson(sb.toString(), MediumApiMe::class.java)
    }
    return null
}

fun newPost(token: String, mediumApiPost: MediumApiPost): Boolean {

    val userID = getUserData(token)?.data?.id ?: throw RuntimeException("Can NOT get user info")

    val request = Request.Builder()
        .url("https://api.medium.com/v1/users/$userID/posts")
        .post(mediumApiPost.toJson().toRequestBody(contentType = "application/json".toMediaType()))
        .addHeader("Authorization", "Bearer $token")
        .build()
    val response = client.newCall(request).execute()

    return response.code == 201
}
