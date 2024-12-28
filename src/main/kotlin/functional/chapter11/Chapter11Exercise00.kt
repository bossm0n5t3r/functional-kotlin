package functional.chapter11

import java.time.LocalDateTime
import java.util.UUID

object Chapter11Exercise00 {
    class StudentService(
        private val studentRepository: StudentRepository,
        private val studentFactory: StudentFactory,
        private val logger: Logger,
    ) {
        fun addStudent(addStudentRequest: AddStudentRequest): Student? =
            addStudentRequest
                .let { studentFactory.produceStudent(it) }
                ?.also { studentRepository.addStudent(it) }

        fun getStudent(studentId: String): ExposedStudent? =
            studentId
                .let { studentRepository.getStudent(it) }
                ?.also { logger.log("Student found: $it") }
                ?.let { studentFactory.produceExposed(it) }

        fun getStudents(semester: String): List<ExposedStudent> =
            produceGetStudentsRequest(semester)
                .let { studentRepository.getStudents(it) }
                .also { logger.log("${it.size} students in $semester") }
                .map { studentFactory.produceExposed(it) }

        private fun produceGetStudentsRequest(semester: String): GetStudentsRequest =
            GetStudentsRequest().apply {
                expectedSemester = semester
                minResult = 3.0
            }
    }

    class StudentFactory(
        private val timeProvider: TimeProvider,
        private val uuidGenerator: UuidGenerator,
    ) {
        fun produceExposed(student: Student): ExposedStudent =
            ExposedStudent(
                name = student.name,
            )

        fun produceStudent(student: AddStudentRequest): Student? =
            Student(
                name = student.name,
                semester = student.semester,
                result = student.result,
                creationTime = timeProvider.now(),
                id = uuidGenerator.generate(),
            )
    }

    interface StudentRepository {
        fun addStudent(student: Student)

        fun getStudent(id: String): Student?

        fun getStudents(request: GetStudentsRequest): List<Student>
    }

    class GetStudentsRequest {
        var minResult: Double? = null
        var expectedSemester: String? = null
    }

    data class AddStudentRequest(
        val name: String,
        val semester: String,
        val result: Double,
    )

    data class Student(
        val id: String,
        val name: String,
        val creationTime: LocalDateTime,
        val semester: String,
        val result: Double,
    )

    data class ExposedStudent(
        val name: String,
    )

    interface Logger {
        fun log(message: String)
    }

    interface TimeProvider {
        fun now(): LocalDateTime
    }

    class RealTimeProvider : TimeProvider {
        override fun now(): LocalDateTime = LocalDateTime.now()
    }

    class FakeTimeProvider : TimeProvider {
        var now: LocalDateTime = INIT_TIME

        fun cleanup() {
            now = INIT_TIME
        }

        override fun now(): LocalDateTime = now

        companion object {
            val INIT_TIME = LocalDateTime.of(2020, 1, 1, 0, 0)
        }
    }

    interface UuidGenerator {
        fun generate(): String
    }

    class RealUuidGenerator : UuidGenerator {
        override fun generate(): String = UUID.randomUUID().toString()
    }

    class FakeUuidGenerator : UuidGenerator {
        var constantUuid: String? = null
        private var counter: Int = 0

        fun cleanup() {
            counter = 0
            constantUuid = null
        }

        override fun generate(): String =
            constantUuid
                ?: (counter++).toString()
    }

    class FakeLogger : Logger {
        var messages: List<String> = emptyList()

        fun cleanup() {
            messages = emptyList()
        }

        override fun log(message: String) {
            this.messages += message
        }
    }

    class FakeStudentRepository : StudentRepository {
        private var students: List<Student> = emptyList()

        fun cleanup() {
            students = emptyList()
        }

        override fun addStudent(student: Student) {
            students += student
        }

        override fun getStudent(id: String): Student? =
            students
                .find { it.id == id }

        override fun getStudents(request: GetStudentsRequest): List<Student> =
            students
                .filter { request.expectedSemester == null || it.semester == request.expectedSemester }
                .filter { request.minResult == null || it.result >= request.minResult!! }
    }
}
