package com.tonypepe.mediumapi

import com.tonypepe.mediumapi.api.MediumApiPost
import com.tonypepe.mediumapi.api.newPost
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import javafx.stage.Stage
import tornadofx.*

fun main() {
    launch<MyApp>()
}

class MyApp() : App(Root::class) {
    override fun start(stage: Stage) {
        stage.isResizable = false
        super.start(stage)
    }
}

class Root : View() {
    private val token = SimpleStringProperty("")
    private val postTitle = SimpleStringProperty("")
    private val postContent = SimpleStringProperty("")

    override val root = vbox {
        form {
            fieldset(text = "New Post") {
                field(text = "Token") {
                    textfield(token)
                }
                field(text = "Title") {
                    textfield { postTitle }
                }
                field(text = "Content") {
                    textarea(postContent)
                }
                buttonbar {
                    button(text = "OK") {
                        action {
                            runAsync {
                                println(token)
                                println(postTitle)
                                println(postContent)
                                var post =
                                    MediumApiPost(postTitle.value, postContent.value, MediumApiPost.CONTENT_FORMAT_MD)
                                newPost(token.value, post)
                            } ui { success ->
                                if (success) {
                                    alert(Alert.AlertType.INFORMATION, "Success")
                                } else {
                                    alert(Alert.AlertType.ERROR, "Error")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

