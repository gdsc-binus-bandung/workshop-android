import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebasecrud.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import java.util.UUID


class UserViewModel : ViewModel() {
    //initiate Firebase
    private val databaseReference = FirebaseDatabase.getInstance().getReference("users")
    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> get() = _userList

    //Read Method
    init {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val users = mutableListOf<User>()
                for (userSnapshot in snapshot.children) {
                    val user = userSnapshot.getValue(User::class.java)
                    user?.let { users.add(it) }
                }
                _userList.postValue(users)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
            }
        })
    }

    //Create Method
    fun createUser(user: User) {
        val userId = UUID.randomUUID().toString()
        databaseReference.child(userId).setValue(user.copy(id = userId))
    }

    //Delete Method
    fun deleteUser(user: User) {
        // Find the user in the database and remove it
        val query: Query = databaseReference.orderByChild("name").equalTo(user.name)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (userSnapshot in snapshot.children) {
                    userSnapshot.ref.removeValue()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Handle database error
            }
        })
    }

    //update method
    fun updateUser(userId: String, updatedUser: User) {
        // Ensure that both user ID and updated user ID are not empty
        if (userId.isNotEmpty() && updatedUser.id.isNotEmpty()) {
            // Get a reference to the user node based on the ID
            val userReference = databaseReference.child(userId)

            // Update user data in the Firebase Realtime Database
            userReference.setValue(updatedUser)
                .addOnSuccessListener {
                    Log.d("UserViewModel", "User updated successfully with ID: $userId")
                }
                .addOnFailureListener {
                    Log.e("UserViewModel", "Failed to update user with ID: $userId")
                }
        } else {
            Log.e("UserViewModel", "Invalid user ID or updated user ID")
        }
    }

    //getUserID Method
    fun getUserById(userId: String?): User? {
        // Check if userId is not null
        if (userId != null) {
            // Find the user with the specified ID in the LiveData value
            val user = _userList.value?.find { it.id == userId }

            // Log the information
            if (user != null) {
                Log.d("UserViewModel", "User found with ID: $userId")
            } else {
                Log.d("UserViewModel", "User not found with ID: $userId")
            }

            // Log the user list size for additional debugging
            Log.d("UserViewModel", "User list size: ${_userList.value?.size}")
            return user
        } else {
            // Log that the user ID is null
            Log.d("UserViewModel", "User ID is null")
            return null
        }
    }


}
