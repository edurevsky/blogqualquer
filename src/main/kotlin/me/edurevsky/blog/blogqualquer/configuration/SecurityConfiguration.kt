package me.edurevsky.blog.blogqualquer.configuration

import me.edurevsky.blog.blogqualquer.security.JWTAuthenticationFilter
import me.edurevsky.blog.blogqualquer.security.JWTLoginFilter
import me.edurevsky.blog.blogqualquer.security.JWTUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.header.writers.StaticHeadersWriter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


/**
 * Using deprecated WebSecurityConfigurerAdapter.
 */
@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JWTUtil,
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http
            ?.headers()?.addHeaderWriter(StaticHeadersWriter("Access-Control-Allow-Origin", "*"))
        ?.and()
        ?.authorizeHttpRequests()
            ?.antMatchers(HttpMethod.POST, "/login")
                ?.permitAll()
            ?.antMatchers(HttpMethod.GET, "/api/v1/posts/{id}")
                ?.permitAll()
            ?.antMatchers(HttpMethod.GET, "/api/v1/posts/rendered/{id}")
                ?.permitAll()
            ?.antMatchers(HttpMethod.GET, "/api/v1/posts")
                ?.permitAll()
            ?.antMatchers("/api/v1/posts/**")
                ?.hasAnyAuthority("ADMIN")
            ?.antMatchers(HttpMethod.GET, "/posts/**")
                ?.permitAll()
            ?.anyRequest()
            ?.authenticated()
        ?.and()
            ?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ?.and()
            ?.csrf()
        ?.and()
            ?.cors()
            ?.disable()

            ?.addFilterBefore(JWTLoginFilter(authManager = authenticationManager(), jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter::class.java)
            ?.addFilterBefore(JWTAuthenticationFilter(jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter::class.java)
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth
            ?.userDetailsService(userDetailsService)
            ?.passwordEncoder(passwordEncoder())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}

@Configuration
@EnableWebMvc
class CorsConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("http://localhost:3000")
    }
}