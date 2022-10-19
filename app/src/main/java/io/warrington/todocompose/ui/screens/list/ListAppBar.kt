package io.warrington.todocompose.ui.screens.list

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import io.warrington.todocompose.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import io.warrington.todocompose.data.models.Priority
import io.warrington.todocompose.ui.theme.topAppBarBackgroundColor
import io.warrington.todocompose.ui.theme.topAppBarContentColor
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import io.warrington.todocompose.components.PriorityItem
import io.warrington.todocompose.ui.theme.Typography

@Composable
fun ListAppBar() {
    DefaultListAppBar(
        onSearchClick = {},
        onSortClick = {},
        onDeleteClicked = {}
    )
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topAppBarBackgroundColor
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "Search",
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.topAppBarContentColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(ContentAlpha.disabled),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = { onCloseClicked() }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close Icon",
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.topAppBarContentColor,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        )
    }
}

@Composable
fun DefaultListAppBar(
    onSearchClick: () -> Unit,
    onSortClick: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Tasks",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        actions = {
            ListAppBarActions(onSearchClick, onSortClick, onDeleteClicked)
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Composable
fun ListAppBarActions(
    onSearchClick: () -> Unit,
    onSortClick: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    SearchAppBarAction(onSearchClick)
    SortActionBarAction(onSortClick)
    DeleteAllAction(onDeleteClicked)
}

@Composable
fun DeleteAllAction(
    onDeleteClicked: () -> Unit
) {
    var expended by remember {
        mutableStateOf(false)
    }
    IconButton(
        onClick = { expended = true }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_menu),
            contentDescription = "Sort Tasks",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
        DropdownMenu(
            expanded = expended,
            onDismissRequest = { expended = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    expended = false
                    onDeleteClicked()
                }
            ) {
                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = "Delete All",
                    style = Typography.subtitle2
                )
            }
        }
    }
}


@Composable
fun SearchAppBarAction(
    onSearchClick: () -> Unit
) {
    IconButton(
        onClick = { onSearchClick() }
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search Tasks",
            tint = MaterialTheme.colors.topAppBarContentColor
        )

    }
}

@Composable
fun SortActionBarAction(
    onSortClick: (Priority) -> Unit
) {

    var expended by remember {
        mutableStateOf(false)
    }
    IconButton(
        onClick = { expended = true }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_list),
            contentDescription = "Sort Tasks",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
        DropdownMenu(
            expanded = expended,
            onDismissRequest = { expended = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    expended = false
                    onSortClick(Priority.LOW)
                },
            ) {
                PriorityItem(priority = Priority.LOW)
            }
            DropdownMenuItem(onClick = { expended = false }) {
                PriorityItem(priority = Priority.MEDIUM)
            }
            DropdownMenuItem(onClick = { expended = false }) {
                PriorityItem(priority = Priority.HIGH)
            }
            DropdownMenuItem(onClick = { expended = false }) {
                PriorityItem(priority = Priority.NONE)
            }
        }
    }
}


@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar(
        onSearchClick = {},
        onSortClick = {},
        onDeleteClicked = {}
    )
}

@Composable
@Preview
private fun SearchAppBarPreview() {
    SearchAppBar(
        text = "Coffee",
        onTextChange = {},
        onCloseClicked = {},
        onSearchClicked = {}
    )
}