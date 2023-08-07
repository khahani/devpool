package chapter3

fun add(a: Int, b: Int) = a + b

val addition: (Int) -> (Int) -> Int = { a -> { b -> a + b } }

typealias IntBinOp = (Int) -> (Int) -> Int

val add: IntBinOp = { a -> { b -> a + b } }
val mul: IntBinOp = { a -> { b -> a * b } }

fun main() {
    println(add(2, 3))
    println(addition(2)(3))
    println(add(2)(3))
    println(mul(4)(6))
}

