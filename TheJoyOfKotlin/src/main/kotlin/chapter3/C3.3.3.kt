package chapter3

val compose1: ((Int) -> Int) -> ((Int) -> Int) -> (Int) -> Int = { x -> { y -> { z -> x(y(z)) } } }

val compose2 = { x: (Int) -> Int ->
    { y: (Int) -> Int ->
        { z: Int ->
            x(y(z))
        }
    }
}

typealias IntUnaryOp = (Int) -> Int

val compose3: (IntUnaryOp) -> (IntUnaryOp) -> IntUnaryOp = { x -> { y -> { z -> x(y(z)) } } }

val square: IntUnaryOp = { it * it }
val triple: IntUnaryOp = { it * 3 }
fun main() {
    val squareOfTriple1 = compose1 {
        it * it
    }() {
        it * 3
    }

    val squareOfTriple2 = compose3(square)(triple)

    val tripleOfSquare = compose2(triple)(square)

    val squareOfSquareOfTriple = compose3(square)(squareOfTriple2)

    println(squareOfTriple1(2))
    println(squareOfTriple2(2))
    println(tripleOfSquare(2))
    println(squareOfSquareOfTriple(2))
}