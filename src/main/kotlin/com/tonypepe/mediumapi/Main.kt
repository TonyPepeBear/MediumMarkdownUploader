package com.tonypepe.mediumapi


fun main() {
    val mediumApiPost = MediumApiPost("Test", "# Test\ntttt\n## tt\n tttt", "markdown")
    println(mediumApiPost.toJson())



}
