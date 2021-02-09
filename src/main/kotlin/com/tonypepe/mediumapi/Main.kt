package com.tonypepe.mediumapi

import com.tonypepe.mediumapi.gui.PostForm
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
    override val root = vbox {
        this += PostForm()
    }
}

