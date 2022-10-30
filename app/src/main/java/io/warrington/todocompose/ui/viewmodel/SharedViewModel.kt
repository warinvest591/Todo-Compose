package io.warrington.todocompose.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.warrington.todocompose.data.models.ToDoTask
import io.warrington.todocompose.repositories.TodoRepository
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

    private val allTasksFromDatabase = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)

    val searchAppBarState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)
    val searchTextState: MutableState<String> = mutableStateOf("")
    val allTasks: StateFlow<RequestState<List<ToDoTask>>> = allTasksFromDatabase

    fun getAllTasks() {
        // Here we are using coroutines to retrieve data with lifecyle-aware components
        viewModelScope.launch {
            allTasksFromDatabase.value = RequestState.Loading
            try {
                repository.getAllTasks.collect {
                    allTasksFromDatabase.value = RequestState.Success(it)
                }
            } catch (e: Exception) {
                allTasksFromDatabase.value = RequestState.Error(e)
            }
{}        }
    }

    private val _selectedTask: MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTask: StateFlow<ToDoTask?> = _selectedTask

    fun getSelectedTask(taskId: Int?) {
        taskId?.let {
            viewModelScope.launch {
                repository.getSelectedTask(taskId = taskId).collect { task ->
                    _selectedTask.value = task
                }
            }
        }
    }
}