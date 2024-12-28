package functional.chapter11

import functional.chapter11.Chapter11Exercise00.AddStudentRequest
import functional.chapter11.Chapter11Exercise00.ExposedStudent
import functional.chapter11.Chapter11Exercise00.FakeLogger
import functional.chapter11.Chapter11Exercise00.FakeStudentRepository
import functional.chapter11.Chapter11Exercise00.FakeTimeProvider
import functional.chapter11.Chapter11Exercise00.FakeUuidGenerator
import functional.chapter11.Chapter11Exercise00.Student
import functional.chapter11.Chapter11Exercise00.StudentFactory
import functional.chapter11.Chapter11Exercise00.StudentService
import org.junit.jupiter.api.AfterEach
import java.time.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class Chapter11Exercise00Test {
    private val logger = FakeLogger()
    private val userRepository = FakeStudentRepository()
    private val timeProvider = FakeTimeProvider()
    private val uuidGenerator = FakeUuidGenerator()
    private val studentFactory = StudentFactory(timeProvider, uuidGenerator)
    private val studentService = StudentService(userRepository, studentFactory, logger)

    @AfterEach
    fun cleanup() {
        logger.cleanup()
        userRepository.cleanup()
        timeProvider.cleanup()
        uuidGenerator.cleanup()
    }

    @Test
    fun `should add student`() {
        // Given
        val time1 = LocalDateTime.of(2023, 5, 6, 7, 8)
        timeProvider.now = time1
        val studentId = "1234"
        uuidGenerator.constantUuid = studentId
        val addStudentRequest =
            AddStudentRequest(
                name = "Marc",
                semester = "1",
                result = 4.0,
            )

        // When
        val result = studentService.addStudent(addStudentRequest)

        // Then
        val expected =
            Student(
                name = "Marc",
                semester = "1",
                result = 4.0,
                creationTime = time1,
                id = studentId,
            )
        assertEquals(expected, result)
        assertEquals(expected, userRepository.getStudent(studentId))
    }

    @Test
    fun `should get student`() {
        // Given
        val studentId = "1234"
        uuidGenerator.constantUuid = studentId
        val addStudentRequest =
            AddStudentRequest(
                name = "Marc",
                semester = "1",
                result = 4.0,
            )
        studentService.addStudent(addStudentRequest)

        // When
        val result = studentService.getStudent(studentId)

        // Then
        val expected =
            ExposedStudent(
                name = "Marc",
            )
        assertEquals(expected, result)
    }

    @Test
    fun `should log when getting student`() {
        // Given
        val time1 = LocalDateTime.of(2023, 5, 6, 7, 8)
        timeProvider.now = time1
        val studentId = "1234"
        uuidGenerator.constantUuid = studentId
        val addStudentRequest =
            AddStudentRequest(
                name = "Marc",
                semester = "1",
                result = 4.0,
            )
        studentService.addStudent(addStudentRequest)

        // When
        val result = studentService.getStudent(studentId)

        // Then
        val expected =
            listOf(
                "Student found: Student(id=1234, name=Marc, creationTime=2023-05-06T07:08, semester=1, result=4.0)",
            )
        assertEquals(expected, logger.messages)
    }

    @Test
    fun `should return null when getting non-existing student`() {
        // Given
        val studentId = "1234"
        uuidGenerator.constantUuid = studentId
        val addStudentRequest =
            AddStudentRequest(
                name = "Marc",
                semester = "1",
                result = 4.0,
            )
        studentService.addStudent(addStudentRequest)

        // When
        val result = studentService.getStudent("1")

        // Then
        assertEquals(null, result)
    }

    @Test
    fun `should get students list and log its size`() {
        // Given
        listOf(
            AddStudentRequest("Alex", "Semester1", 4.0),
            AddStudentRequest("Bob", "Semester2", 4.0),
            AddStudentRequest("Celine", "Semester1", 2.0),
            AddStudentRequest("Diana", "Semester1", 5.0),
            AddStudentRequest("Ester", "Semester2", 3.0),
            AddStudentRequest("Fiona", "Semester1", 4.0),
            AddStudentRequest("Gina", "Semester2", 2.9),
            AddStudentRequest("Helen", "Semester1", 3.0),
            AddStudentRequest("Irene", "Semester1", 4.0),
            AddStudentRequest("Jack", "Semester2", 4.0),
            AddStudentRequest("Kate", "Semester1", 5.0),
            AddStudentRequest("Linda", "Semester1", 3.0),
            AddStudentRequest("Marc", "Semester1", 2.0),
        ).forEach { studentService.addStudent(it) }

        // When
        val result1 = studentService.getStudents("Semester1")

        // Then should receive students from the same semester and with result >= 3.0
        assertEquals(
            listOf(
                ExposedStudent("Alex"),
                ExposedStudent("Diana"),
                ExposedStudent("Fiona"),
                ExposedStudent("Helen"),
                ExposedStudent("Irene"),
                ExposedStudent("Kate"),
                ExposedStudent("Linda"),
            ),
            result1,
        )
        assertEquals(listOf("7 students in Semester1"), logger.messages)

        // Given
        logger.cleanup()

        // When
        val result = studentService.getStudents("Semester2")

        // Then should receive students from the semester and with result >= 3.0
        assertEquals(
            listOf(
                ExposedStudent("Bob"),
                ExposedStudent("Ester"),
                ExposedStudent("Jack"),
            ),
            result,
        )
        assertEquals(listOf("3 students in Semester2"), logger.messages)
    }
}
