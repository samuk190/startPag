package com.yami.pag

import com.fasterxml.jackson.databind.ObjectMapper

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

@ExtendWith(MockitoExtension::class, SpringExtension::class)
@WebMvcTest(UserController::class)
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `test create user`() {
        val user = User(/* provide user parameters */)
        whenever(userRepository.save(any<User>())).thenReturn(user)

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk)
        // Add more expect statements as needed
    }

    @Test
    fun `test get all users`() {
        val users = listOf(User(/* parameters */), User(/* parameters */))
        whenever(userRepository.findAll()).thenReturn(users)

        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().json(objectMapper.writeValueAsString(users)))
    }

    @Test
    fun `test get user by id`() {
        val user = User(/* parameters */)
        val userId = 1L
        whenever(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user))

        mockMvc.perform(get("/users/$userId")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().json(objectMapper.writeValueAsString(user)))
    }

    @Test
    fun `test update user`() {
        val userId = 1L
        // Assuming User class is correctly initialized here.
        val existingUser = User(103)
        val updatedUser = existingUser.copy(103)

        whenever(userRepository.findById(userId)).thenReturn(Optional.of(existingUser))
        // Ensure updatedUser is not null. If it's a data class, make sure it's properly initialized.
        whenever(userRepository.save(any<User>())).thenReturn(updatedUser)

        mockMvc.perform(put("/users/$userId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk)
    }

    @Test
    fun `test delete user`() {
        val userId = 1L
        val user = User(/* parameters */)

        whenever(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user))

        mockMvc.perform(delete("/users/$userId")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
    }

}