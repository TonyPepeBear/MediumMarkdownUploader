package com.tonypepe.mediumapi

import tornadofx.*

fun main() {
    launch<MyApp>()
}

class MyApp() : App()

class Root : View() {
    override val root = vbox {
        button {
            runAsync {

            }
        }
    }
}

