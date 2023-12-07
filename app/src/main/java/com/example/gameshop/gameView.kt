package com.example.gameshop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import com.example.gameshop.ui.theme.GameShopTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun gameLayout(navController: NavController) {
    //initate the input variable
    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.whalelogo),
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .width(200.dp)
                .padding(top = 35.dp)
        )
        // Title
        Text(
            text = "Welcome to Game Shop",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color(0xFFFFAE42),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Enter your Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, bottom = 10.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xFF86c5da))
        )

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Enter your Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 20.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xFF86c5da))
        )
        Button(
            onClick = {
                      navController.navigate("${Routes.gameListView.route}/${name?.takeIf { it.isNotBlank() }}")
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .width(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1450A3))
        ) {
            Text(text = "See Game List")
        }
    }
}


