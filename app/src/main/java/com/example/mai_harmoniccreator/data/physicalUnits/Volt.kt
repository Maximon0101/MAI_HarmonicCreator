package com.example.mai_harmoniccreator.data.physicalUnits

@JvmInline
value class Volt(
    val value: Double
) {
    operator fun compareTo(other: Volt) = value.compareTo(other.value)
    operator fun unaryMinus() = value.unaryMinus()
    operator fun plus(other: Volt) = value.plus(other.value).v
    operator fun minus(other: Volt) = value.minus(other.value).v
    operator fun times(other: Double) = value.times(other).v
    operator fun div(other: Double) = value.div(other).v

}

val Double.v
    get() = Volt(this)

val Int.v
    get() = toDouble().v
