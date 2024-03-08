package com.yami.pag

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User,Long> {
}