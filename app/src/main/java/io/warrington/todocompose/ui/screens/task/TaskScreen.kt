package io.warrington.todocompose.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import io.warrington.todocompose.data.models.Priority
import io.warrington.todocompose.data.models.ToDoTask
import io.warrington.todocompose.ui.viewmodel.SharedViewModel
import io.warrington.todocompose.util.Action
import io.warrington.todocompose.util.Action.NO_ACTION

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {

    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToTaskScreen = { action ->
                    when {
                        action == NO_ACTION -> navigateToListScreen(action)
                        sharedViewModel.validateFields() -> navigateToListScreen(action)
                        else -> displayToast(context = context)
                    }
                }
            )
        },
        content = {
            selectedTask?.let {
                TaskContent(
                    title = title,
                    onTitleChange = {
                        sharedViewModel.updateTitle(it)
                    },
                    description = description,
                    onDescriptionChange = {
                        sharedViewModel.description.value = it
                    },
                    priority = priority,
                    onPrioritySelected = {
                        sharedViewModel.priority.value = it
                    }
                )
            }
        }
    )
}

fun displayToast(context: Context) {
    Toast.makeText(context, "Fields are empty", Toast.LENGTH_SHORT)
}
