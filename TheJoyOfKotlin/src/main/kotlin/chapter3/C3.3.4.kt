package chapter3

val square1: (Int) -> Int = { it * it }
val triple1: (Int) -> Int = { it * 3 }

//region Optional
val isZero: (Int) -> Boolean = { it == 0 }
val trueOrFalse: (Boolean) -> Long = { if (it) 10 else 100 }
//endregion

fun <T, U, V> higherCompose1(): ((U) -> V) -> ((T) -> U) -> (T) -> V = { f ->
    { g ->
        { x -> f(g(x)) }
    }
}

fun <T, U, V> higherCompose2() =
    { f: (U) -> V ->
        { g: (T) -> U ->
            { x: T -> f(g(x)) }
        }
    }

fun main() {
    println(higherCompose1<Int, Int, Int>()(square1)(triple1)(2))
    println(higherCompose2<Int, Int, Int>()(square1)(triple1)(2))
    //region Optional
    println(higherCompose2<Int, Boolean, Long>()(trueOrFalse)(isZero)(2))
    //endregion
}

