package me.edurevsky.blog.blogqualquer.services

import me.edurevsky.blog.blogqualquer.entities.User

interface UserService {

    fun findById(id: Long): User
}