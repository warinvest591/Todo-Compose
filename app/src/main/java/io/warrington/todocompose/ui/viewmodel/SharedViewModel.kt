package io.warrington.todocompose.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.warrington.todocompose.data.models.Priority
import io.warrington.todocompose.data.models.ToDoTask
import io.warrington.todocompose.repositories.TodoRepository
import io.warrington.todocompose.util.Constant.MAX_TITLE_LENGTH
import io.warrington.todocompose.util.RequestState
import io.warrington.todocompose.util.SearchAppBarState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
* This serves as the View Model in MVVM
* Here we are creating business logic to retrieve data from the database
* */
@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    val id: MutableState<Int> = mutableStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

    private val _allTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    private val _selectedTask: MutableStateFlow<ToDoTask?> = MutableStateFlow(null)

    val searchAppBarState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)
    val searchTextState: MutableState<String> = mutableStateOf("")
    val allTasks: StateFlow<RequestState<List<ToDoTask>>> = _allTasks
    val selectedTask: StateFlow<ToDoTask?> = _selectedTask

    fun getAllTasks() {
        // Here we are using coroutines to retrieve data with lifecyle-aware components
        viewModelScope.launch {
            _allTasks.value = RequestState.Loading
            try {
                repository.getAllTasks.collect {
                    _allTasks.value = RequestState.Success(it)
                }
            } catch (e: Exception) {
                _allTasks.value = RequestState.Error(e)
            }
{}        }
    }

    fun getSelectedTask(taskId: Int?) {
        taskId?.let {
            viewModelScope.launch {
                repository.getSelectedTask(taskId = taskId).collect { task ->
                    _selectedTask.value = task
                }
            }
        }
    }

    fun updateTaskFields(selectedTask: ToDoTask?) {
        if (selectedTask != null) {
            id.value = selectedTask.id
            title.value = selectedTask.title
            description.value = selectedTask.description
            priority.value = selectedTask.priority
        } else {
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.LOW
        }
    }

    fun updateTitle(newTitle: String) {
        if (newTitle.length < MAX_TITLE_LENGTH) {
            title.value = newTitle
        }
    }

    fun validateFields(): Boolean {
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }

}