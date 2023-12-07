package DataType

fun main() {
    // Contoh penggunaan array
    val num: Array<Int> = arrayOf(1, 2, 3, 4, 5)
    val word: Array<String> = arrayOf("build", "budi", "sapi")

    // Mengakses panjang array
    val numSize = num.size
    val wordSize = word.size

    println("Panjang arrayNum: $numSize")
    println("Panjang arrayWord: $wordSize")

    // Mengecek apakah array kosong
    val isEmptyNum = num.isEmpty()
    val isEmptyWord = word.isEmpty()

    println("ArrayNum kosong: $isEmptyNum")
    println("ArrayWord kosong: $isEmptyWord")

    // Mendapatkan elemen pertama dan terakhir dari array
    val firstNum = num.first()
    val lastWord = word.last()

    println("Elemen pertama arrayNum: $firstNum")
    println("Elemen terakhir arrayWord: $lastWord")

    // Menghitung jumlah dan rata-rata (untuk array numerik)
    val sumNum = num.sum()
    val averageNum = num.average()

    println("Jumlah arrayNum: $sumNum")
    println("Rata-rata arrayNum: $averageNum")
}
