package com.tonypepe.mediumapi

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.*

val client = OkHttpClient.Builder()
    .build()

fun main() {
    val token = "2e8bb2a3707b096e29338da7d36bcf6ac684fe1c649822180ab82787c98af5352"
//    println(getUserData(token))

    print(
        newPost(
            token,
            MediumApiPost("Testing", "# Testing\n``` kotlin\nvar i = 0\n```\n## H2\n", MediumApiPost.CONTENT_FORMAT_MD)
        )
    )
}


fun getUserData(token: String): MediumApiMe? {
    val request = Request.Builder()
        .url("https://api.medium.com/v1/me")
        .get()
        .addHeader("Authorization", "Bearer $token")
        .build()
    val response = client.newCall(request).execute()
    response.body?.run {
        var scanner = Scanner(byteStream())
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
    var response = client.newCall(request).execute()

    return response.code == 201
}
