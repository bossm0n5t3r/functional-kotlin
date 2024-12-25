package functional.chapter08

object Chapter08Exercise00 {
    data class StudentJson(
        val name: String?,
        val surname: String?,
        val result: Double,
        val pointsInSemester: Int,
    )

    fun List<StudentJson>.getPassingSurnames(): List<String> =
        this
            .filter { it.result >= 50 && it.pointsInSemester >= 15 }
            .mapNotNull { it.surname }
}
