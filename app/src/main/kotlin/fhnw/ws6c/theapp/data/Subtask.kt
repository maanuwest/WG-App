package fhnw.ws6c.theapp.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class Subtask(val id: Int,
                   var name: String,
//                   var isCompleted: Boolean = false
                   )
{
   var isCompleted = mutableStateOf(false)
}