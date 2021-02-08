package com.tonypepe.mediumapi

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

val client = OkHttpClient.Builder()
    .build()

fun main() {
    val mediumApiPost = MediumApiPost("Test", "# Test\ntttt\n## tt\n tttt", "markdown")
    val token = "2e8bb2a3707b096e29338da7d36bcf6ac684fe1c649822180ab82787c98af5352"
    println(mediumApiPost.toJson())
    val request = Request.Builder()
        .url("https://api.medium.com/v1/users/15e681e4c26fc94dce939e018e1af7df548fe57dddfde24578e20948ee334d97a/posts")
        .post(mediumApiPost.toJson().toRequestBody(contentType = "application/json".toMediaType()))
        .addHeader("Authorization", "Bearer $token")
        .build()
    println(request)
    var message = client.newCall(request).execute()
    println(message)
    println(message.body?.byteString())


//    val request = Request.Builder()
//        .url("https://api.medium.com/v1/me")
//        .get()
//        .addHeader("Authorization", "Bearer $token")
//        .build()
//    println(client.newCall(request).execute())
}

