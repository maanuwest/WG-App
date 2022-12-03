package fhnw.ws6c.theapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import fhnw.ws6c.theapp.data.Subtask
import fhnw.ws6c.theapp.data.Task
import fhnw.ws6c.theapp.data.User
import fhnw.ws6c.theapp.model.TheModel.subtaskList
import fhnw.ws6c.theapp.model.TheModel.tasks
import fhnw.ws6c.theapp.model.TheModel.users
import java.util.*

object TheModel {
    var activeScreen by mutableStateOf(Screens.Dashboard)
    var users: MutableList<User> = mutableStateListOf()
    var tasks: MutableList<Task> = mutableStateListOf()
    var subtaskList = mutableStateListOf<Subtask>()
    var editingTask  by mutableStateOf(Task())
    var activeTask by mutableStateOf(initData())
}

fun initData(): Task{

    val userList = mutableListOf<User>()
    val taskList = mutableStateListOf<Task>()

    //Add initial Users
    val user1 = User(1,"Luca", Color(0xFFFFE781),2)
    val user2 = User(2,"Manuel", Color(0xFFFF9492),3)
    val user3 = User(3,"Kotlin", Color(0xFF98EDF1),1)
    userList.add(user1)
    userList.add(user2)
    userList.add(user3)
    users = userList
    val subtaskList1 = mutableListOf<Subtask>()
    val subtask11 = Subtask(11,"WC reinigen")
    val subtask12 = Subtask(12,"Lavabo reinigen")
    val subtask13 = Subtask(13,"Dusche reinigen")
    subtaskList1.add(subtask11)
    subtaskList1.add(subtask12)
    subtaskList1.add(subtask13)
    subtaskList.add(subtask11)
    subtaskList.add(subtask12)
    subtaskList.add(subtask13)

    val task1 = Task(1,"Bad reinigen", user1, 6,subtaskList1, Date(2022,4,16),7,1)
    taskList.add(task1)
    tasks = taskList

    return task1
}

fun getNthUserByPoints(n: Int): User{

//    people.sortWith(compareByDescending { it.firstName })
    users.sortWith(compareByDescending { it.score })
//    users.sortedWith(compareByScore)
//    Collections.sort(users,compareByScore)
    return users[n]
}

