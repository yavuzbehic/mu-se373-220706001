package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoApp()
        }
    }
}

@Composable
fun TodoApp() {
    var todoItems by remember { mutableStateOf(mutableListOf<String>()) }
    var newItemText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Text Field to add new todo item
        TextField(
            value = newItemText,
            onValueChange = { newItemText = it },
            label = { Text("Add Todo") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    todoItems.add(newItemText)
                    newItemText = ""
                }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button to add new todo item
        Button(
            onClick = {
                todoItems.add(newItemText)
                newItemText = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Todo list with checkboxes
        TodoList(todoItems)
    }
}

@Composable
fun TodoList(todoItems: List<String>) {
    Column {
        todoItems.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Checkbox(checked = false, onCheckedChange = null)
                Text(text = item, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}
