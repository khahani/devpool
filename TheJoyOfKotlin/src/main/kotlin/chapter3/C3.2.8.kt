package chapter3

fun square(n: Int): Int = n * n

fun triple(n: Int): Int = n * 3

fun compose(f: (Int) -> Int, g: (Int) -> Int): (Int) -> Int = { f(g(it)) }

val squareOfTriple = compose(::square, ::triple)

fun main() {
    println(squareOfTriple(2))
}