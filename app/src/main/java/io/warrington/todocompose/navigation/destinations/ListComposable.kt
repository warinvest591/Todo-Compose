package io.warrington.todocompose.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.warrington.todocompose.ui.screens.list.ListScreen
import io.warrington.todocompose.ui.viewmodel.SharedViewModel

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = "list/{action}",
        arguments = listOf(navArgument("action"){
            type = NavType.StringType
        })
    ) {
        // Here we are designing our screen
        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )
    }
}