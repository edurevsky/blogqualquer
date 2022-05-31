package me.edurevsky.blog.blogqualquer.repositories

import me.edurevsky.blog.blogqualquer.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByUsername(username: String?): User?
}
