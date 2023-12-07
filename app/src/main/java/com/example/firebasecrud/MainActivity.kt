package com.example.firebasecrud

import UserViewModel
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebasecrud.ui.theme.FirebaseCRUDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            FirebaseCRUDTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //navigation Method
                    NavHost(navController, startDestination = Routes.menuView.route) {
                        composable(Routes.menuView.route) {
                            val viewModel: UserViewModel = viewModel()
                            menuView(viewModel = viewModel, navController = navController)
                        }
                        composable(Routes.userListView.route) {
                            val viewModel: UserViewModel = viewModel()
                            userListView(viewModel = viewModel, navController = navController)
                        }
                        composable("${Routes.editUserView.route}/{userId}") { backStackEntry ->
                            val viewModel: UserViewModel = viewModel()
                            val userId = backStackEntry.arguments?.getString("userId")
                            val user = viewModel.getUserById(userId)
                            if (user != null) {
                                editUserLayout(user = user, viewModel = viewModel, navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}


