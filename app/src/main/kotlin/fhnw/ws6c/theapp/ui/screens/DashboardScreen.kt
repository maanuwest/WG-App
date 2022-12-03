package fhnw.ws6c.theapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.ws6c.theapp.data.Subtask
import fhnw.ws6c.theapp.data.Task
import fhnw.ws6c.theapp.model.Screens
import fhnw.ws6c.theapp.model.TheModel
import fhnw.ws6c.theapp.ui.buttonColor
import fhnw.ws6c.theapp.ui.fontColor

@Composable
fun DashboardScreen(model: TheModel){
    Column(
        Modifier.padding(32.dp),
    ){
        Text("Current Tasks", fontSize = 32.sp, color = fontColor)
        for(task in model.tasks){
            Box(Modifier
                .padding(top = 10.dp)){
                Card(
                    Modifier
                        .background(MaterialTheme.colors.secondary)
                        .clickable {
                            model.activeTask = task
                            model.activeScreen = Screens.TaskOverview
                        },
                ){
                    Box(modifier = Modifier.height(100.dp).background(MaterialTheme.colors.secondary)){
                        Row(
                            Modifier
                                .background(task.assignee.color)
                                .width(10.dp)
                                .fillMaxHeight()){
                            Box(modifier = Modifier
                                .background(task.assignee.color)
                                .width(10.dp))
                        }
                        Row(
                            Modifier.padding(start = 10.dp)
                        ) {
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp, 20.dp, 32.dp, 16.dp)) {
                                Text(task.name, fontSize = 20.sp, fontWeight = FontWeight.Medium)
                                Text("Responsable: " + task.assignee.name, fontSize = 14.sp, fontWeight = FontWeight.Normal)
                                LinearProgressIndicator(
                                    progress = getProgress(task.subtasks),
                                    backgroundColor = MaterialTheme.colors.primary,
                                    color = task.assignee.color,
                                    modifier = Modifier
                                        .padding(top = 5.dp)
                                        .fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            }
        }
        Box(Modifier.align(Alignment.End).padding(top = 10.dp)){
            FloatingActionButton(
                onClick = { model.activeScreen = Screens.TaskConfiguration },
                content = {
                    Icon(imageVector = Icons.Default.Add,"")
                },
                backgroundColor = buttonColor
            )
        }
    }
}

fun getProgress(subtaskList: List<Subtask>): Float{
    var counter = 0
    var isDoneCounter = 0
    for (subtask in subtaskList){
        counter++
        if(subtask.isCompleted.value){
            isDoneCounter++
        }
    }
    return isDoneCounter.toFloat() / counter
}