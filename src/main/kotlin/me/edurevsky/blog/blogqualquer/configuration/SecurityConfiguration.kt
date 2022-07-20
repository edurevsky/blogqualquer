package me.edurevsky.blog.blogqualquer.configuration

import me.edurevsky.blog.blogqualquer.security.JWTAuthenticationFilter
import me.edurevsky.blog.blogqualquer.security.JWTLoginFilter
import me.edurevsky.blog.blogqualquer.security.JWTUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val jwtUtil: JWTUtil,
    private val authenticationConfiguration: AuthenticationConfiguration
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf().disable()
            .formLogin().disable()
            .httpBasic()
            .and()
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeRequests()
            .antMatchers(POST, "/login").permitAll()
            .antMatchers(GET, "/api/v1/posts/{id}", "/api/v1/posts").permitAll()
            .antMatchers("/api/v1/posts").hasAnyAuthority("ADMIN")
            .anyRequest()
            .authenticated()
        http
            .addFilterBefore(
                JWTLoginFilter(
                    jwtUtil = jwtUtil,
                    authManager = authenticationConfiguration.authenticationManager
                ), UsernamePasswordAuthenticationFilter::class.java
            )
            .addFilterBefore(
                JWTAuthenticationFilter(jwtUtil = jwtUtil),
                UsernamePasswordAuthenticationFilter::class.java
            )
        return http.build()
    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry
                    .addMapping("/**")
                    .allowedOrigins("*")
            }
        }
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}
