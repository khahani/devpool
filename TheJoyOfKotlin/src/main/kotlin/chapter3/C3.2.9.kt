package chapter3

fun <T, U, V> compose(
    f: (U) -> V,
    g: (T) -> U,
): (T) -> V = { f(g(it)) }

fun squareInt(n: Int): Int = n * n
fun tripleInt(n: Int): Int = n * 3
fun squareLong(n: Long): Long = n * n
fun tripleLong(n: Long): Long = n * 3
fun main() {
    val squareOfTripleInt = compose(::squareInt, ::tripleInt)
    val squareOfTripleLong = compose(::squareLong, ::tripleLong)

    val squareOfSquareOfTriple = compose(squareOfTripleInt, ::squareInt)

    println(squareOfTripleInt(2))
    println(squareOfTripleLong(5))
    println(squareOfSquareOfTriple(2))
}