package me.edurevsky.blog.blogqualquer.repositories

import me.edurevsky.blog.blogqualquer.entities.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long>
