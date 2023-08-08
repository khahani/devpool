package chapter3

import kotlin.math.sin

val f: (Double) -> Double = { Math.PI / 2 - it }
val sin: (Double) -> Double = Math::sin
val cos: Double = compose(f, sin)(2.0)
val cosValue: Double = compose({ x: Double -> Math.PI / 2 - x }, Math::sin)(2.0)
val cosHOF = higherCompose1<Double, Double, Double>()()
{ x: Double -> Math.PI / 2 - x }(Math::sin)
val cosHofValue = cosHOF(2.0)
fun cos(arg: Double) = compose(f, sin)(arg)
fun cosTypeInferenceLambda(arg: Double) = compose(
    { x: Double -> Math.PI / 2 - x },
    { x: Double -> sin(x) },
)(arg)

fun cosTypeInferenceReferenceFunction(arg: Double) = compose(
    { Math.PI / 2 - it },
    Math::sin,
)(arg)
