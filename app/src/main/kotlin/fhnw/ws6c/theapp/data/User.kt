package fhnw.ws6c.theapp.data

import androidx.compose.ui.graphics.Color

data class User(val id: Int,
                val name: String,
                val color: Color,
                val score: Int
) {
}