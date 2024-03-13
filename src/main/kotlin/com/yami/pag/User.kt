package com.yami.pag

import jakarta.persistence.*
import java.time.LocalDate
import java.util.Date

@Entity(name="users")
data class User (
    @Id @GeneratedValue(generator = "ENTITY_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ENTITY_SEQ", sequenceName = "ENTITY_SEQ")
    var id: Long? = null,
        @Column(length = 32)
    var apelido: String? = null,
        @Column(length = 255, nullable = false, unique = true)
    var nome    : String? = null,
        @Column(nullable = false)
    var birthDate : LocalDate? = null,
        @ElementCollection
    @CollectionTable(name = "user_technologies", joinColumns = [JoinColumn(name = "user_id")])
    @Column(name = "technology", length = 32)
    var technologies: List<String> = mutableListOf()
    )