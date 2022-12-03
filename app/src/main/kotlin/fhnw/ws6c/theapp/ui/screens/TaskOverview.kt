package fhnw.ws6c.theapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.ws6c.theapp.data.Task
import fhnw.ws6c.theapp.model.Screens
import fhnw.ws6c.theapp.model.TheModel
import fhnw.ws6c.theapp.model.TheModel.activeTask
import fhnw.ws6c.theapp.ui.buttonColor
import fhnw.ws6c.theapp.ui.fontColor

@Composable
fun TaskOverviewScreen(model: TheModel){
    Column(
        Modifier.padding(32.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(model.activeTask.name, fontSize = 32.sp, color = fontColor)
            EditButton(model)
        }
        Row(modifier = Modifier.fillMaxWidth().padding(top=50.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text("Subtasks", fontSize = 20.sp, color = fontColor, fontWeight = FontWeight.Bold)
            Text(getAmountOfActiveSubtasksFinished().toString() +"/"+activeTask.subtasks.size, color = activeTask.assignee.color, fontSize = 24.sp)
        }
        for (subtask in model.activeTask.subtasks){
//            var completed by remember { mutableStateOf(subtask.isCompleted) }
            Card(
                Modifier
                    .padding(top=16.dp)
                    .background(MaterialTheme.colors.secondary)
                    .fillMaxWidth()
                    .clickable {
                        subtask.isCompleted.value=!(subtask.isCompleted.value)
//                        completed=subtask.isCompleted
                    }
            ) {
                Row(modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(25.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(subtask.name, fontSize = 20.sp, fontWeight = FontWeight.Medium)
                    if(subtask.isCompleted.value){
                        Icon(imageVector = Icons.Filled.Check,"", tint= activeTask.assignee.color)
                    }else{
                        Icon(imageVector = Icons.Outlined.Circle,"", tint= activeTask.assignee.color)
                    }
                }
            }
        }
    }
}
@Composable
fun EditButton(model: TheModel){
    Button(onClick = {
        model.editingTask = model.activeTask
        model.activeScreen=Screens.TaskConfiguration
    }) {
        Icon(Icons.Default.Edit ,contentDescription = "content description", tint= buttonColor)
    }
}

fun getAmountOfActiveSubtasksFinished(): Int{
    var counter = 0
    for(subtask in activeTask.subtasks){
        if(subtask.isCompleted.value){
            counter++
        }
    }
    return counter
}