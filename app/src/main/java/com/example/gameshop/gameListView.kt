package com.example.gameshop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun gameListLayout(name: String?){
    Scaffold(
        topBar ={
            TopAppBar(title = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .size(60.dp)
                                .padding(8.dp),
                            painter = painterResource(id = R.drawable.game_logo),
                            contentDescription = null
                        )
                        Text(text = "Hello $name")
                    }
                }
            },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF0080FF)
                )
            )
        }
    ) {
        innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color(0xFF39A7FF))
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ){
            items(games){game->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF87C4FF)
                    )

                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(painter = painterResource(id = game.image),
                            contentDescription = null,
                            modifier= Modifier
                                .size(60.dp)
                                .clip(shape = CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(
                            modifier = Modifier
                                .weight(1f) // Take up remaining space in the row
                        ) {
                            Text(text = game.title, fontWeight = FontWeight.Bold)
                            Text(text = game.description)
                        }

                        // Right side: Rating in a separate Column
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            Text(text = "${game.rating}", modifier = Modifier.padding(end=3.dp))
                            // Yellow star icon
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_star_24),
                                contentDescription = null,
                                tint = Color(0xFFFF9209),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true, backgroundColor = 0xFF0080FF)
//@Composable
//fun gameListPreview() {
//    GameShopTheme {
//        gameListLayout()
//    }
//}