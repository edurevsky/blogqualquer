package me.edurevsky.blog.blogqualquer.rest.controllers

import me.edurevsky.blog.blogqualquer.security.Credentials
import me.edurevsky.blog.blogqualquer.security.JWTUtil
import me.edurevsky.blog.blogqualquer.services.UserDetail
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/login")
class LoginController(
    val authenticationManager: AuthenticationManager,
    val jwtUtil: JWTUtil
) {

    @PostMapping
    fun login(@RequestBody credentials: Credentials): ResponseEntity<Any> {
        val authenticate = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                credentials.username,
                credentials.password
            )
        )
        val user = authenticate.principal as UserDetail
        return ResponseEntity.ok()
            .header(HttpHeaders.AUTHORIZATION, jwtUtil.generateToken(user.username, user.authorities))
            .build()
    }
}