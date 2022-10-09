package io.warrington.todocompose.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.warrington.todocompose.util.Constant.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class ToDoTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority
)