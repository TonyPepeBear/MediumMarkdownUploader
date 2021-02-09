package com.tonypepe.mediumapi.gui

import com.tonypepe.mediumapi.api.MediumApiPost
import com.tonypepe.mediumapi.api.newPost
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import okhttp3.HttpUrl.Companion.toHttpUrl
import tornadofx.*
import java.awt.Desktop

class PostForm : View() {
    private val token = SimpleStringProperty("")
    private val postTitle = SimpleStringProperty("")
    private val postContent = SimpleStringProperty("")
    private val openBrowser = SimpleBooleanProperty(true)
    private val formatType = SimpleStringProperty("markdown")

    override val root = form {
        fieldset(text = "New Post") {
            field(text = "Token") {
                textfield(token)
            }
            field(text = "Title") {
                textfield(postTitle)
            }
            combobox(formatType, values = listOf("markdown", "html"))
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
                                MediumApiPost(postTitle.value, postContent.value, formatType.value)
                            newPost(token.value, post)
                        } ui { url ->
                            if (openBrowser.value and url.isNotBlank()) {
                                Desktop.getDesktop().browse(url.toHttpUrl().toUri())
                                alert(Alert.AlertType.INFORMATION, "Successful", url)
                            }
                        }
                    }
                }
            }
        }
    }
}