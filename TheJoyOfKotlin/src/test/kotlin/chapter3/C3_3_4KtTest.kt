package chapter3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class C3_3_4KtTest {
    @Test
    fun testHigherAndThen() {
        val f: (Double) -> Int = { a -> (a * 3).toInt() }
        val g: (Long) -> Double = { a -> a + 2.0 }

        assertEquals(Integer.valueOf(9), f(g(1L)))
        assertEquals(Integer.valueOf(9), higherAndThen<Long, Double, Int>()(g)(f)(1L))
    }
}