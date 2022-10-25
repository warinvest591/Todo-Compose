package io.warrington.todocompose.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.warrington.todocompose.data.models.ToDoTask
import io.warrington.todocompose.repositories.TodoRepository
import io.warrington.todocompose.util.SearchAppBarState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    private val allTasksFromDatabase = MutableStateFlow<List<ToDoTask>>(emptyList())

    val searchAppBarState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)
    val searchTextState: MutableState<String> = mutableStateOf("")
    val allTasks: StateFlow<List<ToDoTask>> = allTasksFromDatabase

    fun getAllTasks() {
        viewModelScope.launch {
            repository.getAllTasks.collect {
                allTasksFromDatabase.value = it
            }
        }
    }
}