package com.yami.pag

import jakarta.persistence.*
import java.util.Date

@Entity(name="users")
data class User (
    @Id @GeneratedValue(generator = "ENTITY_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ENTITY_SEQ", sequenceName = "ENTITY_SEQ")
    var id: Long? = null,
    var apelido: String? = null,
    var nome    : String? = null,
    var birthDate : Date? = null,
//    var listaTecnologias : Array<String>? = null,
    )