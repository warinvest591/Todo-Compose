package io.warrington.todocompose.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.warrington.todocompose.util.Action

fun NavGraphBuilder.taskComposable(
    navigateToTaskScreen: (Action) -> Unit
) {
    composable(
        route = "task/{taskId}",
        arguments = listOf(navArgument("taskId"){
            type = NavType.IntType
        })
    ) {

    }
}