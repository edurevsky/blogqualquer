package me.edurevsky.blog.blogqualquer.security

import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import me.edurevsky.blog.blogqualquer.entities.Role
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtil(
    private val userDetailsService: UserDetailsService
) {

    @Value("\${application.secret}")
    private lateinit var applicationSecret: String
    private val expirationLimit = 60 * 2 * 1000

    fun generateToken(username: String?, authorities: List<Role>): String? {
        return Jwts.builder().setSubject(username)
                    .claim("role", authorities)
                    .setExpiration(Date(System.currentTimeMillis() + expirationLimit))
                    .signWith(SignatureAlgorithm.HS256, applicationSecret.toByteArray())
                    .setHeaderParam("typ", Header.JWT_TYPE)
                    .compact()
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            parseClaims(jwt)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    fun getAuthentication(jwt: String?): Authentication {
        val username = parseClaims(jwt).body.subject
        val user = userDetailsService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(user, null, user.authorities)
    }

    private fun parseClaims(jwt: String?) = Jwts.parser().setSigningKey(applicationSecret.toByteArray()).parseClaimsJws(jwt)
}