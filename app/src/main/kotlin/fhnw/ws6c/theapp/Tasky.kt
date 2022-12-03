package fhnw.ws6c.theapp

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import fhnw.ws6c.EmobaApp
import fhnw.ws6c.theapp.data.Subtask
import fhnw.ws6c.theapp.data.Task
import fhnw.ws6c.theapp.data.User
import fhnw.ws6c.theapp.model.TheModel
import fhnw.ws6c.theapp.ui.AppUI
import java.time.Instant.now
import java.time.LocalDate.now
import java.util.*


object Tasky : EmobaApp {
    private lateinit var model : TheModel

    override fun initialize(activity: ComponentActivity) {
        model = TheModel
    }

    @Composable
    override fun CreateUI() {
        AppUI(model)
    }

}

