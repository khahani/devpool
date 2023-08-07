package chapter3

import chapter3.other.doubleX

fun main() {
    println(multiplyBy2(2))
    val foo = MyClass()
    val multiplyBy2: (Int) -> Int = foo::double
    println(multiplyBy2(2))
    val multiplyBy2X = ::doubleX // needs to import
    println(multiplyBy2X(2))
    val multipleBy2Companion = MyClass.Companion::double
    println(multipleBy2Companion(2))
}

fun double(n: Int): Int = n * 2

//val multiplyBy2: (Int) -> Int = { n -> double(n) }

val multiplyBy2: (Int) -> Int = ::double

class MyClass {
    fun double(n: Int) = n * 2

    companion object {
        fun double(n: Int) = n * 2
    }
}