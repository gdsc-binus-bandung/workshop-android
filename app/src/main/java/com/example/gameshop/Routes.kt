package com.example.gameshop

//initate the screen
sealed class Routes(val route:String){
    object gameView: Routes("gameHome")
    object gameListView: Routes("gameList")
}
