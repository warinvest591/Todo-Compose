package io.warrington.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import io.warrington.todocompose.navigation.destinations.listComposable
import io.warrington.todocompose.navigation.destinations.taskComposable
import io.warrington.todocompose.ui.viewmodel.SharedViewModel

@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = "list/{action}") {
        listComposable(
            navigateToTaskScreen = screen.task,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToTaskScreen = screen.list
        )
    }
}