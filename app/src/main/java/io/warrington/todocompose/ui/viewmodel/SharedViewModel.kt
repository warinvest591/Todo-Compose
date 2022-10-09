package io.warrington.todocompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.warrington.todocompose.data.models.ToDoTask
import io.warrington.todocompose.repositories.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {

    private val allTasksFromDatabase  = MutableStateFlow<List<ToDoTask>>(emptyList())
    val allTasks: StateFlow<List<ToDoTask>> = allTasksFromDatabase

    fun getAllTasks() {
        viewModelScope.launch {
            repository.getAllTasks.collect {
                allTasksFromDatabase.value = it
            }
        }
    }
}