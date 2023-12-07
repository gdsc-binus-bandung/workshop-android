package DataType

fun main(){
    //membuat rentang 1 sampai 5
    val range1 = 1..5
    val range1String = range1.joinToString()
    println("Range1: $range1String")

    //membuat rentang dari 5 turun ke 1
    val range = 5 downTo 1

    //membuat rentang 1 sampai 10 dengan langkah 2
    val range3 = 1..10 step 2

    //membuat rentang hingga nilai sebelum 5
    val range4 = 1 until 5
}