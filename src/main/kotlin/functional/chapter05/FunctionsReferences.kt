package functional.chapter05

object FunctionsReferences {
    object Code00 {
        data class Complex(
            val real: Double,
            val imaginary: Double,
        ) {
            fun doubled(): Complex = Complex(this.real * 2, this.imaginary * 2)

            fun times(num: Int) = Complex(real * num, imaginary * num)
        }

        fun zeroComplex(): Complex = Complex(0.0, 0.0)

        fun makeComplex(
            real: Double = 0.0,
            imaginary: Double = 0.0,
        ) = Complex(real, imaginary)

        fun Complex.plus(other: Complex): Complex = Complex(real + other.real, imaginary + other.imaginary)

        fun Int.toComplex() = Complex(this.toDouble(), 0.0)
    }

    object Code01 {
        fun add(
            a: Int,
            b: Int,
        ) = a + b

        fun main() {
            val f = ::add // function reference
            assert(f.isOpen.not())
            assert(f.visibility.toString() == "PUBLIC")
            // The above statements require `kotlin-reflect`
            // dependency
        }
    }

    object Code02 {
        fun add(
            a: Int,
            b: Int,
        ) = a + b

        fun main() {
            val f: (Int, Int) -> Int = ::add
            // an alternative to:
            // val f: (Int, Int) -> Int = { a, b -> add(a, b) }
            assert(f(10, 20) == 30)
        }
    }

    object Code03 {
        fun zeroComplex(): Complex = Complex(0.0, 0.0)

        fun makeComplex(
            real: Double = 0.0,
            imaginary: Double = 0.0,
        ) = Complex(real, imaginary)

        data class Complex(
            val real: Double,
            val imaginary: Double,
        )

        fun main() {
            val f1: () -> Complex = ::zeroComplex
            assert(f1() == Complex(real = 0.0, imaginary = 0.0))

            val f2: (Double, Double) -> Complex = ::makeComplex
            assert(f2(1.0, 2.0) == Complex(real = 1.0, imaginary = 2.0))
        }
    }

    object Code04 {
        data class Number(
            val num: Int,
        ) {
            fun toFloat(): Float = num.toFloat()

            fun times(n: Int): Number = Number(num * n)
        }

        fun main() {
            val numberObject = Number(10)
            // member function reference
            val float: (Number) -> Float = Number::toFloat
            // `toFloat` has no parameters, but its function type
            // needs a receiver of type `Number`
            assert(float(numberObject) == 10.0f)

            val multiply: (Number, Int) -> Number = Number::times
            assert(multiply(numberObject, 4) == Number(num = 40))
            // `times` has one parameter of type `Int`, but its
            // function type also needs a receiver of type `Number`
        }
    }

    object Code05 {
        fun sum(
            a: Int,
            b: Int,
        ) = (a..b).fold(0, Int::plus)

        fun product(
            a: Int,
            b: Int,
        ) = (a..b).fold(1, Int::times)
    }

    object Code06 {
        data class Complex(
            val real: Double,
            val imaginary: Double,
        ) {
            fun doubled(): Complex = Complex(this.real * 2, this.imaginary * 2)

            fun times(num: Int) = Complex(real * num, imaginary * num)
        }

        fun main() {
            val c1 = Complex(1.0, 2.0)

            val f1: (Complex) -> Complex = Complex::doubled
            println(f1(c1)) // Complex(real=2.0, imaginary=4.0)

            val f2: (Complex, Int) -> Complex = Complex::times
            println(f2(c1, 4)) // Complex(real=4.0, imaginary=8.0)
        }
    }

    object Code07 {
        class TeamPoints(
            val points: List<Int>,
        ) {
            fun <T> calculatePoints(operation: (List<Int>) -> T): T = operation(points)
        }

        fun main() {
            val teamPoints = TeamPoints(listOf(1, 3, 5))

            val sum =
                teamPoints
                    .calculatePoints(List<Int>::sum)
            assert(sum == 9)

            val avg =
                teamPoints
                    .calculatePoints(List<Int>::average)
            assert(avg == 3.0)

            val invalid = String?::isNullOrBlank
            assert(invalid(null))
            assert(invalid("  "))
            assert(invalid("AAA").not())
        }
    }

    object Code08 {
        class Box<T>(
            private val value: T,
        ) {
            fun unbox(): T = value
        }

        fun main() {
            val unbox = Box<String>::unbox
            val box = Box("AAA")
            assert(unbox(box) == "AAA")
        }
    }

    object Code09 {
        data class Number(
            val num: Int,
        ) {
            fun toFloat(): Float = num.toFloat()

            fun times(n: Int): Number = Number(num * n)
        }

        fun main() {
            val num = Number(10)
            // bounded function reference
            val getNumAsFloat: () -> Float = num::toFloat
            // There is no need for receiver type in function type,
            // because reference is already bound to an object
            assert(getNumAsFloat() == 10.0f)

            val multiplyNum: (Int) -> Number = num::times
            assert(multiplyNum(4) == Number(num = 40))
        }
    }

    object Code10 {
        object SuperUser {
            fun getId() = 0
        }

        fun main() {
            val myId = SuperUser::getId
            assert(myId() == 0)

            val obj =
                object {
                    fun cheer() {
                        println("Hello")
                    }
                }
            val f = obj::cheer
            f() // Hello
        }
    }

    object Code11 {
        data class Complex(
            val real: Double,
            val imaginary: Double,
        )

        fun main() {
            // constructor reference
            val produce: (Double, Double) -> Complex = ::Complex
            assert(produce(1.0, 2.0) == Complex(real = 1.0, imaginary = 2.0))
        }
    }

    object Code12 {
        class StudentId(
            val value: Int,
        )

        class UserId(
            val value: Int,
        ) {
            constructor(studentId: StudentId) : this(studentId.value)
        }

        fun main() {
            val ints: List<Int> = listOf(1, 1, 2, 3, 5, 8)
            val studentIds: List<StudentId> = ints.map(::StudentId)
            val userIds: List<UserId> = studentIds.map(::UserId)

            assert(studentIds.size == ints.size)
            assert(userIds.size == ints.size)
        }
    }

    object Code13 {
        object Robot {
            fun moveForward() {
                // ...
            }

            fun moveBackward() {
                // ...
            }
        }

        fun main() {
            Robot.moveForward()
            Robot.moveBackward()

            val action1: () -> Unit = Robot::moveForward
            val action2: () -> Unit = Robot::moveBackward
        }
    }

    object Code14 {
        class Drone {
            fun setOff() {}

            fun land() {}

            companion object {
                fun makeDrone(): Drone = Drone()
            }
        }

        fun main() {
            val maker: () -> Drone = Drone.Companion::makeDrone
        }
    }

    object Code15 {
        private fun foo(i: Int) = 1

        private fun foo(str: String) = "AAA"

        fun main() {
            assert(foo(123) == 1)
            assert(foo("") == "AAA")
        }
    }

    object Code16 {
        fun foo(i: Int) = 1

        fun foo(str: String) = "AAA"

        fun main() {
            val fooInt: (Int) -> Int = ::foo
            assert(fooInt(123) == 1)

            val fooStr: (String) -> String = ::foo
            assert(fooStr("") == "AAA")
        }
    }

    object Code17 {
        class StudentId(
            val value: Int,
        )

        data class UserId(
            val value: Int,
        ) {
            constructor(studentId: StudentId) : this(studentId.value)
        }

        fun main() {
            val intToUserId: (Int) -> UserId = ::UserId
            assert(intToUserId(1) == UserId(value = 1))

            val studentId = StudentId(2)
            val studentIdToUserId: (StudentId) -> UserId = ::UserId
            assert(studentIdToUserId(studentId) == UserId(value = 2))
        }
    }

    object Code18 {
        data class Complex(
            val real: Double,
            val imaginary: Double,
        )

        fun main() {
            val c1 = Complex(1.0, 2.0)
            val c2 = Complex(3.0, 4.0)

            // property reference
            val getter: (Complex) -> Double = Complex::real

            assert(getter(c1) == 1.0)
            assert(getter(c2) == 3.0)

            // bounded property reference
            val c1ImgGetter: () -> Double = c1::imaginary
            assert(c1ImgGetter() == 2.0)
        }
    }
}
