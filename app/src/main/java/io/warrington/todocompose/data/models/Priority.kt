package io.warrington.todocompose.data.models

import androidx.compose.ui.graphics.Color
import io.warrington.todocompose.ui.theme.HighPriorityColor
import io.warrington.todocompose.ui.theme.LowPriorityColor
import io.warrington.todocompose.ui.theme.MediumPriorityColor
import io.warrington.todocompose.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}