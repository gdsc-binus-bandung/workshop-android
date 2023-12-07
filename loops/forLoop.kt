package loops

fun main(){
//    for array
    val name = arrayOf("marvel", "hanel", "ethan")
    for(i in name){
        println(i)
    }
    //    for range
    for(value in 0..10){
        println(value)
    }

    for(value in 1000 downTo 0 step 5){
        println(value)
    }
}