package functional.chapter08

object Chapter08Exercise05 {
    fun List<Student>.makePassingStudentsList(): String =
        filter { it.pointsInSemester > 15 && it.result >= 50.0 }
            .sortedWith(compareBy({ it.surname }, { it.name }))
            .joinToString("\n") { "${it.name} ${it.surname}, ${it.result}" }

    data class Student(
        val name: String,
        val surname: String,
        val result: Double,
        val pointsInSemester: Int,
    )
}
