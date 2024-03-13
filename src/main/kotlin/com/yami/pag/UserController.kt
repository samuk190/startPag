package com.yami.pag

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.HttpStatus;
@RestController
@RequestMapping("/users")
class UserController(private val repository: UserRepository) {

    @PostMapping
    fun create(@RequestBody user: User) = repository.save(user)

    @GetMapping
    fun getAll(): List<User> = repository.findAll()

    @GetMapping("/{id}")
    fun getbyId(@PathVariable id: Long): ResponseEntity<User> = repository.findById(id).map{
        ResponseEntity.ok(it)
    }.orElse(ResponseEntity.notFound().build())

    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody user:User) : ResponseEntity<User> =
            repository.findById(id).map{
                val userToUpdate = it.copy(
                        nome = it.nome,
                        apelido = it.apelido

                )
                ResponseEntity.ok(repository.save(userToUpdate))
            }.orElse(ResponseEntity.notFound().build())


    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<Void> =
                repository.findById(id).map{
                repository.delete(it)
                ResponseEntity<Void>(HttpStatus.OK)
            }.orElse(ResponseEntity.notFound().build())
}