fun tambah(a: Int, b:Int): Int{
    val total = a+b
    return total
}

fun hello(){
    println("hello world")
}

fun double(a: Int):Int = a*2
fun hi(name: String): Unit = print("Hi $name")

fun main(){
    hello()
    val result = tambah(10,10)
    hi("GDSC")
}