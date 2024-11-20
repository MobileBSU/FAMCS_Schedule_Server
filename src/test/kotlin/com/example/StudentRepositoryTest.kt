package com.example

import com.example.dao.student.StudentDao
import com.example.model.SignInParams
import com.example.model.SignUpParams
import com.example.model.Student
import com.example.repository.student.StudentRepositoryImpl
import com.example.security.hashPassword
import com.example.util.Response
import io.ktor.http.HttpStatusCode
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class StudentRepositoryTest {

    private val studentDao: StudentDao = mock(StudentDao::class.java)
    private val studentRepository = StudentRepositoryImpl(studentDao)

    @Test
    fun `signUp should return error if student already exists`() = runBlocking {
        val email = "test@example.com"
        val params = SignUpParams("Test User", email, "password")

        val studentExample = Student(
            id = 1,
            name = "Test User",
            email = email,
            password = "password",
            avatar = null
        )

        `when`(studentDao.findByEmail(email)).thenReturn(studentExample)

        val result = studentRepository.signUp(params)
        assertEquals(HttpStatusCode.Conflict, (result as Response.Error).code)
        assertEquals("A student with this email already exists!", result.data.errorMessage)
    }

    @Test
    fun `signUp should return success when student is registered`() = runBlocking {
        val email = "test@example.com"
        val params = SignUpParams("Test Student", email, "password")

        val studentExample = Student(
            id = 1,
            name = "Test Student",
            email = email,
            password = "password",
            avatar = null
        )


        `when`(studentDao.findByEmail(email)).thenReturn(null)
        `when`(studentDao.insert(params)).thenReturn(studentExample)

        val result = studentRepository.signUp(params)

        assertEquals(HttpStatusCode.OK, (result as Response.Success))
    }

    @Test
    fun `signIn should return error if student not found`() = runBlocking {
        val params = SignInParams("nonexistent@example.com", "password")

        `when`(studentDao.findByEmail(params.email)).thenReturn(null)

        val result = studentRepository.signIn(params)

        assertEquals(HttpStatusCode.NotFound, (result as Response.Error).code)
        assertEquals("Invalid credentials, no user with this email!", result.data.errorMessage)
    }

    @Test
    fun `signIn should return success when credentials are valid`() = runBlocking {
        val email = "test@example.com"
        val params = SignInParams(email, "password")
        val student = mock(Student::class.java).apply {
            `when`(password).thenReturn(hashPassword("password"))
            `when`(id).thenReturn(1)
            `when`(name).thenReturn("Test User")
        }

        `when`(studentDao.findByEmail(email)).thenReturn(student)

        val result = studentRepository.signIn(params)

        assertEquals(HttpStatusCode.OK, (result as Response.Success).data.data?.id)
        assertEquals("Test User", result.data.data?.name)
    }

    @Test
    fun `signIn should return error if password is incorrect`() = runBlocking {
        val email = "test@example.com"
        val params = SignInParams(email, "wrongpassword")
        val student = mock(Student::class.java).apply {
            `when`(password).thenReturn(hashPassword("password"))
        }

        `when`(studentDao.findByEmail(email)).thenReturn(student)

        val result = studentRepository.signIn(params)

        assertEquals(HttpStatusCode.Forbidden, (result as Response.Error).code)
        assertEquals("Invalid credentials, wrong password", result.data.errorMessage)
    }

}