package ConditionalExpression

fun main(){
    val examValue = 90
    if(examValue>=90){
        println("Score A")
    }
    else if(examValue in 80..89){
        println("score B")
    }
    else{
        println("not A")
    }
}