package com.example.mai_harmoniccreator.data.physicalUnits

import kotlinx.serialization.Serializable
import kotlin.time.Duration.Companion.seconds

@JvmInline
@Serializable
value class Hertz(
    val value: Double
) {
    operator fun compareTo(other: Hertz) = value.compareTo(other.value)
    operator fun unaryMinus() = value.unaryMinus()
    operator fun plus(other: Hertz) = value.plus(other.value).hz
    operator fun minus(other: Hertz) = value.minus(other.value).hz
    operator fun times(other: Double) = value.times(other).hz
    operator fun div(other: Double) = value.div(other).hz

}

val Double.hz
    get() = Hertz(this)

val Int.hz
    get() = toDouble().hz

operator fun Double.div(other: Hertz) = this.div(other.value).seconds
