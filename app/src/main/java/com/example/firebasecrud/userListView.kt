package com.example.firebasecrud

import UserViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun userListView(viewModel: UserViewModel, navController: NavController) {
    var userList by remember { mutableStateOf<List<User>>(emptyList()) }

    LaunchedEffect(viewModel.userList.value) {
        userList = viewModel.userList.value ?: emptyList()
    }

    LazyColumn {
        itemsIndexed(userList) { index, user ->
            // Display each user in a Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                Row {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        // User details
                        Text(text = "Name: ${user.name}")
                        Text(text = "Age: ${user.age}")
                        Text(text = "City: ${user.city}")
                        Text(text = "ID: ${user.id}")

                        // Icons
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_edit),
                                contentDescription = null, // Decorative element
                                tint = Color.Blue,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        // Handle edit icon click
                                        navController.navigate("${Routes.editUserView.route}/${user.id}")
                                    }
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_delete),
                                contentDescription = null, // Decorative element
                                tint = Color.Red,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        // Handle delete icon click
                                        viewModel.deleteUser(user) // Add a function to delete user in your ViewModel
                                        userList = userList.toMutableList().apply { removeAt(index) }
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}



