package com.example.mai_harmoniccreator.data.physicalUnits

@JvmInline
value class Angle private constructor(
    private val value: Double // In radians
) {
    val inRadians
        get() = value
    val inDegrees
        get() = rad2grad(value)

    companion object {
        fun fromRadians(angle: Double): Angle {
            return Angle(angle)
        }
        fun fromDegrees(angle: Double): Angle {
            return Angle(grad2rad(angle))
        }

        private fun rad2grad(radians: Double): Double {
            return (180 * radians) / Math.PI
        }

        private fun grad2rad(radians: Double): Double {
            return (radians * Math.PI) / 180.0
        }
    }
}

val Double.rad
    get() = Angle.fromRadians(this)

val Double.deg
    get() = Angle.fromDegrees(this)