package com.example.firebasecrud

sealed class Routes(val route:String){
    object menuView: Routes("menu")
    object userListView: Routes("userList")
    object editUserView: Routes("editUser")
}

