package functional.chapter08

object Chapter08Exercise04 {
    fun List<StudentGrades>.getBestForScholarship(semester: String): List<StudentGrades> =
        this
            .filter { studentGrades ->
                studentGrades.grades.filter { grade -> grade.semester == semester && grade.passing }.sumOf { grade -> grade.ects } > 30
            }.sortedByDescending { averageGradeFromSemester(it, semester) }
            .take(10)

    fun averageGradeFromSemester(
        student: StudentGrades,
        semester: String,
    ): Double =
        student.grades
            .filter { it.semester == semester }
            .map { it.grade }
            .average()

    data class Grade(
        val passing: Boolean,
        var ects: Int,
        var semester: String,
        var grade: Double,
    )

    data class StudentGrades(
        val studentId: String,
        val grades: List<Grade>,
    )
}
