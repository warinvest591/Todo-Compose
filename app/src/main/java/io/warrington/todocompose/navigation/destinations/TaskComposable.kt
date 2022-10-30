package io.warrington.todocompose.navigation.destinations

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.warrington.todocompose.ui.screens.task.TaskScreen
import io.warrington.todocompose.ui.viewmodel.SharedViewModel
import io.warrington.todocompose.util.Action

fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = "task/{taskId}",
        arguments = listOf(navArgument("taskId") {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments?.getInt("taskId")

        sharedViewModel.getSelectedTask(taskId) // this kicks off search in the database

        val selectedTask by sharedViewModel.selectedTask.collectAsState()
        TaskScreen(
            selectedTask = selectedTask,
            navigateToListScreen = navigateToListScreen
        )
    }
}