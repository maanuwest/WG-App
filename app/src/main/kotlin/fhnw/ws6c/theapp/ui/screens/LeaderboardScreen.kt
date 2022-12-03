package fhnw.ws6c.theapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import fhnw.ws6c.theapp.model.TheModel
import fhnw.ws6c.theapp.model.getNthUserByPoints
import fhnw.ws6c.theapp.ui.fontColor

@Composable
fun LeaderboardScreen(model: TheModel){
    LeaderBox(model = model)
}

@Composable
fun LeaderBox(model: TheModel){
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
    ) {
        val (rect1, title, rect2, rect3, first,second,third,firstScore,secondScore,thirdScore) = createRefs()
        val margin = 32.dp
        Text(getNthUserByPoints(0).name+" leads with "+ getNthUserByPoints(0).score+" Points!", fontSize = 32.sp, color = fontColor, modifier=Modifier.constrainAs(title){
            top.linkTo(parent.top,margin)
            start.linkTo(parent.start,margin)
            end.linkTo(parent.end,margin)
        })
        Box(
            modifier = Modifier
                .clip(RectangleShape)
                .background(getNthUserByPoints(0).color)
                .constrainAs(rect1) {
                    top.linkTo(title.bottom, margin)
                    start.linkTo(parent.start, 76.dp)
                    end.linkTo(parent.end, 76.dp)
                    bottom.linkTo(parent.bottom,margin*6)
                    height = Dimension.fillToConstraints
                    width = Dimension.percent(.4f)
                }
        )
        Text(getNthUserByPoints(0).name, fontSize = 24.sp, color = fontColor, modifier=Modifier.constrainAs(first){
            bottom.linkTo(rect1.top)
            start.linkTo(rect1.start)
            end.linkTo(rect1.end)
        })
        Text(""+getNthUserByPoints(0).score, fontSize = 24.sp, color = MaterialTheme.colors.secondary, modifier=Modifier.constrainAs(firstScore){
            top.linkTo(rect1.top)
            start.linkTo(rect1.start)
            end.linkTo(rect1.end)
        })
        Box(
            modifier = Modifier
                .clip(RectangleShape)
                .background(getNthUserByPoints(1).color)
                .constrainAs(rect2) {
                    top.linkTo(title.bottom, margin * 4)
                    start.linkTo(parent.start, 48.dp)
                    bottom.linkTo(parent.bottom,margin*6)
                    height = Dimension.fillToConstraints
                    width = Dimension.percent(.3f)
                }
        )
        Text(getNthUserByPoints(1).name, fontSize = 24.sp, color = fontColor, modifier=Modifier.constrainAs(second){
            bottom.linkTo(rect2.top)
            start.linkTo(rect2.start)
            end.linkTo(rect2.end)
        })
        Text(""+getNthUserByPoints(1).score, fontSize = 24.sp, color = MaterialTheme.colors.secondary, modifier=Modifier.constrainAs(secondScore){
            top.linkTo(rect2.top)
            start.linkTo(rect2.start)
            end.linkTo(rect2.end)
        })
        Box(
            modifier = Modifier
                .clip(RectangleShape)
                .background(getNthUserByPoints(2).color)
                .constrainAs(rect3) {
                    top.linkTo(title.bottom, margin * 6)
                    end.linkTo(parent.end, 48.dp)
                    bottom.linkTo(parent.bottom,margin*6)
                    height = Dimension.fillToConstraints
                    width = Dimension.percent(.3f)
                }
        )
        Text(getNthUserByPoints(2).name, fontSize = 24.sp, color = fontColor, modifier=Modifier.constrainAs(third){
            bottom.linkTo(rect3.top)
            start.linkTo(rect3.start)
            end.linkTo(rect3.end)
        })
        Text(""+getNthUserByPoints(2).score, fontSize = 24.sp, color = MaterialTheme.colors.secondary, modifier=Modifier.constrainAs(thirdScore){
            top.linkTo(rect3.top)
            start.linkTo(rect3.start)
            end.linkTo(rect3.end)
        })
    }
}
