package io.warrington.todocompose.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import io.warrington.todocompose.data.models.ToDoTask
import io.warrington.todocompose.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToTaskScreen = navigateToListScreen
            )
        },
        content = {}
    )
}