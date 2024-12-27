package functional.chapter08

object Chapter08Exercise06 {
    fun List<Student>.makeBestStudentsList(): String =
        filter { it.pointsInSemester >= 30 && it.result >= 80.0 }
            .sortedByDescending { it.result }
            .take(10)
            .zip(listOf(5000, 3000, 3000, 3000, 1000, 1000, 1000, 1000, 1000, 1000))
            .sortedWith(compareBy({ it.first.surname }, { it.first.name }))
            .joinToString("\n") { "${it.first.name} ${it.first.surname}, ${'$'}${it.second}" }

    data class Student(
        val name: String,
        val surname: String,
        val result: Double,
        val pointsInSemester: Int,
    )
}
