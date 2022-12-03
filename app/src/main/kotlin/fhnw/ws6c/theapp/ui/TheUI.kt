package fhnw.ws6c.theapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.ws6c.theapp.model.Screens
import fhnw.ws6c.theapp.model.TheModel
import fhnw.ws6c.theapp.model.TheModel.activeScreen
import fhnw.ws6c.theapp.ui.screens.DashboardScreen
import fhnw.ws6c.theapp.ui.screens.LeaderboardScreen
import fhnw.ws6c.theapp.ui.screens.TaskConfigurationScreen
import fhnw.ws6c.theapp.ui.screens.TaskOverviewScreen

val darkColors = darkColors(
    primary = Color(22,24,36), //#161824

//    primary = Color(255,0,0), //#161824
    secondary = Color(38,42,54) //#262A36
)

val buttonColor = Color(35,98,243) //#2362F3
val fontColor = Color(207,211,222) //#CFD3DE
val accentColor =Color(159,111,247)

val colors = darkColors
@Composable
fun AppUI(model : TheModel){
    val scaffoldState = rememberScaffoldState()
    with(model){
        MaterialTheme(colors = colors){
            Scaffold(scaffoldState=scaffoldState,
                topBar = { TopBar(model = model) },
                content = {
                    when (model.activeScreen) {
                        Screens.Dashboard -> { DashboardScreen(model) }
                        Screens.Leaderboard -> { LeaderboardScreen(model)  }
                        Screens.TaskOverview -> { TaskOverviewScreen(model) }
                        Screens.TaskConfiguration -> { TaskConfigurationScreen(model) }
                    }
                },
                bottomBar = {BottomBar()}
            )
        }
    }
}

@Composable
fun TopBar(model: TheModel) {
    TopAppBar(
        title = {
            Text(text = model.activeScreen.screenName)
        },
        navigationIcon = {
            if(model.activeScreen != Screens.Dashboard){
                IconButton(onClick = { activeScreen = Screens.Dashboard}) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = fontColor,
        elevation = 10.dp
    )
}

@Composable
fun BottomBar() {
    val items = listOf(
        Screens.Dashboard,
        Screens.Leaderboard
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = buttonColor
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                selected = item == Screens.Dashboard,
                onClick = { activeScreen = item },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.screenName
                    )
                }
            )
        }
    }
}

/*-----------------------------------------------------Previews------------------------------------------------------*/


@Preview
@Composable
fun Preview(){
    MaterialTheme(colors = colors){
        TopAppBar(
            title = {
                Text(text = "Dashboard")
            },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            elevation = 10.dp
        )
    }
}

@Preview
@Composable
fun TaskCard(){
    MaterialTheme(colors = colors){
        Card(
            Modifier
                .background(MaterialTheme.colors.secondary)
                .padding(10.dp)){
            Row() {
                Column(Modifier.background(MaterialTheme.colors.secondary)) {
                    Text("Task name", fontSize = 20.sp, fontWeight = FontWeight.Medium)
                    Text("Responsable: User X", fontSize = 14.sp, fontWeight = FontWeight.Normal)
                    LinearProgressIndicator(
                        progress = 0.5f,
                        backgroundColor = MaterialTheme.colors.primary,
                        color = buttonColor,
                        modifier = Modifier.padding(top = 5.dp)
                    )
                }
            }
        }
    }
}