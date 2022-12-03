package fhnw.ws6c.theapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.ws6c.theapp.data.Subtask
import fhnw.ws6c.theapp.data.Task
import fhnw.ws6c.theapp.model.Screens
import fhnw.ws6c.theapp.model.TheModel
import fhnw.ws6c.theapp.ui.accentColor
import fhnw.ws6c.theapp.ui.buttonColor
import fhnw.ws6c.theapp.ui.fontColor


@Composable
fun TaskConfigurationScreen(model: TheModel) {
    with(model) {
        Column(
            Modifier
                .padding(32.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Task Configuration", fontSize = 32.sp, color = fontColor)
            UnderlinedText(model.editingTask.name, onTextChange = {
                model.editingTask.name = it
            })
            Spacer(modifier = Modifier.height(24.dp))
            TitleText(text = "Repeat every")
            RadioButton(model)
            Spacer(modifier = Modifier.height(24.dp))
            TitleText(text = "Increment")
            UnderlinedText(text = model.editingTask.repeatEveryXDays.toString(), onTextChange = {
                model.editingTask.repeatEveryXDays = it.toInt()
            }, keyboardType = KeyboardType.Number)
            Spacer(modifier = Modifier.height(24.dp))
            TitleText(text = "Assignee")
            ShowAssignees(model = model)
            Spacer(modifier = Modifier.height(24.dp))
            TitleText(text = "Points for completion")
            Spacer(modifier = Modifier.height(24.dp))
            UnderlinedText(text = model.editingTask.points.toString(), onTextChange = {
                model.editingTask.points = it.toInt()
            }, keyboardType = KeyboardType.Number)
            Spacer(modifier = Modifier.height(24.dp))
            TitleText(text = "Subtasks")
            Spacer(modifier = Modifier.height(24.dp))
            SubTaskList(model = model)
            Spacer(modifier = Modifier.height(24.dp))
            saveButton(model)
            Spacer(modifier = Modifier.height(24.dp))

        }
    }

}

//TODO scroll to show textfield when opening keyboard
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UnderlinedText(
    text: String,
    onTextChange: (String) -> Unit = {},
    onClick: (String) -> Unit = {},
    removeOnClick: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Ascii
) {
    var value by remember { mutableStateOf(text) }
    val keyboard = LocalSoftwareKeyboardController.current
    MaterialTheme(colors = fhnw.ws6c.theapp.ui.colors) {
        TextField(
            value = value,
            onValueChange = {
                value = it

                if (value.isNotBlank()) onTextChange(value)
            },
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                textColor = fontColor
            ),
            textStyle = TextStyle(
                color = fontColor,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
            ),
            modifier = Modifier
                .drawBehind {
                    val strokeWidth = Stroke.DefaultMiter
                    val y = size.height - strokeWidth / 2

                    drawLine(
                        accentColor,
                        Offset(0f, y),
                        Offset(size.width, y),
                        strokeWidth,
                    )
                },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                autoCorrect = false,
                keyboardType = keyboardType
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboard?.hide()
                if (value.isNotBlank()) onClick(value)
                if (removeOnClick) value = ""
            }),
        )
    }
}

@Composable
fun TitleText(text: String) {
    Card(
        Modifier
            .background(MaterialTheme.colors.secondary)
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Row() {
            Column(
                Modifier
                    .background(colors.secondary)
                    .fillMaxWidth()
            ) {
                Text(text, fontSize = 18.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun NormalText(text: String) {
    Text(text, fontSize = 18.sp, fontWeight = FontWeight.Medium)
}

@Composable
fun RadioButton(model: TheModel) {

    var state by remember { mutableStateOf(false) }
    var state2 by remember { mutableStateOf(false) }
    var state3 by remember { mutableStateOf(false) }
    if (model.editingTask.repeatMultiplier == 1) state = true
    else if (model.editingTask.repeatMultiplier == 7) state2 = true
    else if (model.editingTask.repeatMultiplier == 30) state3 = true

    var colors = RadioButtonDefaults.colors(
        selectedColor = accentColor
    )
    Row(
        Modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            colors = colors,
            selected = state,
            onClick = {
                state = true
                state2 = false
                state3 = false
                model.editingTask.repeatMultiplier = 1
            }
        )
        NormalText("Day")

        RadioButton(
            colors = colors,
            selected = state2,
            onClick = {
                state2 = true
                state = false
                state3 = false
                model.editingTask.repeatMultiplier = 7
            },
        )
        NormalText("Week")
        RadioButton(
            colors = colors,
            selected = state3,
            onClick = {
                state3 = true
                state = false
                state2 = false
                model.editingTask.repeatMultiplier = 30
            }
        )
        NormalText("Month")
    }
}

//FIXME remove always removes last subtask in UI
@Composable
fun SubTaskList(model: TheModel) {
    with(model) {
        for (subtask in editingTask.subtasks) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RemoveSubtaskButton(accentColor, subtask, model)
                UnderlinedText(text = subtask.name, onTextChange = { s: String ->
                    subtask.name = s
                })
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RemoveSubtaskButton(colors.secondary, Subtask(0, ""), model)
            UnderlinedText("", onClick = { s: String ->
                editingTask.subtasks.add(Subtask(0, s))
            }, removeOnClick = true)
        }
    }
}

@Composable
fun RemoveSubtaskButton(
    color: Color = colors.secondary,
    subtask: Subtask,
    model: TheModel
) {
    FloatingActionButton(
        shape = CircleShape,
        contentColor = fontColor,
        modifier = Modifier
            .background(Color.Transparent)
            .size(40.dp),
        backgroundColor = color,
        onClick = { model.editingTask.subtasks.remove(subtask) }) {
        Icon(Icons.Filled.Remove, "")
    }
}

@Composable
fun saveButton(model: TheModel) {
    with(model) {
        Box(modifier = Modifier.fillMaxSize()) {
            Button(
                //TODO
                onClick = {
                    if (tasks.contains(editingTask)) {
                        tasks.remove(editingTask)
                        editingTask = Task()
                    }
//                    if (activeTask == editingTask) activeTask = Task()
                    activeScreen = Screens.Dashboard
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                modifier = Modifier.align(Alignment.CenterStart)
            )
            { Text("Delete") }
            Button(
                //TODO
                onClick = {
                    if (activeTask != editingTask) tasks.add(editingTask.copy())
                    editingTask = Task()

                    activeScreen = Screens.Dashboard
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
                modifier = Modifier.align(Alignment.CenterEnd)

            )
            { Text("Save") }
        }
    }
}

@Composable
fun ShowAssignees(model: TheModel) {
    var user by remember { mutableStateOf(model.editingTask.assignee) }
    user = model.users.first()
    Row() {
        for (u in model.users) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = {
                    model.editingTask.assignee = u
                    user = u
                }, modifier = Modifier.size(64.dp)) {
                    Icon(
                        Icons.Filled.Face,
                        "",
                        modifier = Modifier.fillMaxSize(),
                        tint = if (user == u) u.color.copy(alpha = 1.0f) else u.color.copy(alpha = 0.5f)
                    )
                }
                Text(u.name)
            }
        }
    }
}