package com.tonypepe.mediumapi.gui

import com.tonypepe.mediumapi.api.MediumApiPost
import com.tonypepe.mediumapi.api.newPost
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import okhttp3.HttpUrl.Companion.toHttpUrl
import tornadofx.*
import java.awt.Desktop

class PostForm : View() {
    private val token = SimpleStringProperty("")
    private val postTitle = SimpleStringProperty("")
    private val postContent = SimpleStringProperty("")
    private val openBrowser = SimpleBooleanProperty(true)

    override val root = form {
        fieldset(text = "New Post") {
            field(text = "Token") {
                textfield(token)
            }
            field(text = "Title") {
                textfield(postTitle)
            }
            field(text = "Content") {
                textarea(postContent)
            }
            checkbox(text = "Open Browser", openBrowser)
            buttonbar {
                button(text = "OK") {
                    action {
                        runAsync {
                            println(token)
                            println(postTitle)
                            println(postContent)
                            val post =
                                MediumApiPost(postTitle.value, postContent.value, MediumApiPost.CONTENT_FORMAT_MD)
                            newPost(token.value, post)
                        } ui { url ->
                            if (openBrowser.value and url.isNotBlank()) {
                                Desktop.getDesktop().browse(url.toHttpUrl().toUri())
                            }
                        }
                    }
                }
            }
        }
    }
}