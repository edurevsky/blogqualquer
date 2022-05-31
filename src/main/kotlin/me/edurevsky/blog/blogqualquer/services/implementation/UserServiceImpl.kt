package me.edurevsky.blog.blogqualquer.services.implementation

import me.edurevsky.blog.blogqualquer.entities.User
import me.edurevsky.blog.blogqualquer.exceptions.UserNotFoundException
import me.edurevsky.blog.blogqualquer.repositories.UserRepository
import me.edurevsky.blog.blogqualquer.services.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService, UserDetailsService {

    override fun findById(id: Long): User {
        return userRepository.findById(id).orElseThrow { UserNotFoundException("User with id '$id' not found.") }
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        TODO("Not yet implemented")
    }
}