package io.warrington.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import io.warrington.todocompose.navigation.destinations.listComposable
import io.warrington.todocompose.navigation.destinations.taskComposable

@Composable
fun SetupNavigation(
    navController: NavHostController
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = "list/{action}") {
        listComposable(
            navigateToTaskScreen = screen.task
        )
        taskComposable(
            navigateToTaskScreen = screen.list
        )
    }
}