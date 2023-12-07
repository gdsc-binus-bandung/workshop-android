//SuperClass
open class Person(
    // Attributes
    val name: String,
    val id: Int,
    val role: String
){
    fun information(){
        println("Name: $name")
        println("NIM: $id")
        println("Role: $role")
    }
}

//Subclass
class Mahasiswa(
    name:String,
    id:Int,
    val jurusan: String
) : Person(name,id, role = "Student"){
    // Method to display information about the student
    fun displayInfo() {
        information()
        println("Jurusan: $jurusan")
    }

}

class Dosen(
    name:String,
    id:Int,
    val matkul: String
) : Person(name,id, role = "Instructor"){
    // Method to display information about the student
    fun displayInfo() {
        information()
        println("Matkul: $matkul")
    }
}

fun main() {
    // Create an instance of the Mahasiswa class
    val mhs1 = Mahasiswa("Kevin", 251424, "cs")

    // Create an instance of the Instructor class
    val dosen1 = Dosen("Dr. Smith", 98765, "math")

    // Call methods on the objects
    mhs1.displayInfo()
    println() // Separate the output
    dosen1.displayInfo()
}





