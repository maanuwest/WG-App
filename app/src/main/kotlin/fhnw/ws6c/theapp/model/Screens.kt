package fhnw.ws6c.theapp.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Task
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screens(var screenName: String, var icon: ImageVector) {
    Dashboard("Dashboard", icon = Icons.Filled.Dashboard),
    TaskOverview("Task overview", icon = Icons.Filled.Task),
    Leaderboard("Leaderboard", icon = Icons.Filled.Leaderboard),
    TaskConfiguration("Task configuration", icon = Icons.Filled.AddTask)
}