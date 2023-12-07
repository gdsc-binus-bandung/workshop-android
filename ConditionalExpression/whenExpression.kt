package ConditionalExpression

fun main(){
    val examValue = 90
    when{
        examValue>=90  -> println("Score A")
        examValue in 80..89 -> println("Score B")
        else -> println("Not A")
    }
}