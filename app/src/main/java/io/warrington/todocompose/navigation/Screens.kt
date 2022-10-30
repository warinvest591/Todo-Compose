package io.warrington.todocompose.navigation

import androidx.navigation.NavHostController
import io.warrington.todocompose.util.Action

// This class handles the navigation in our application
class Screens(navController: NavHostController)  {
    val list: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action.name}") {
            popUpTo("list/{action}") { inclusive = true }
        }
    }

    val task: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }
}