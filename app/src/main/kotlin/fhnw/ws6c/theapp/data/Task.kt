package fhnw.ws6c.theapp.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import java.util.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import java.time.Instant

data class Task(val id: Int,
                var name: String,
                var assignee: User,
                var points: Int,
                var subtasks: MutableList<Subtask>,
                var lastCreation: Date,
                var repeatEveryXDays: Int,
                var repeatMultiplier: Int,
) {
    constructor() : this(0,
        name="Name",
        assignee=User(1,"Luca", Color.Cyan,0),
        points=1,
        mutableStateListOf<Subtask>(),
        Date.from(Instant.now()),
        repeatEveryXDays=1,
        repeatMultiplier=1
    )
}