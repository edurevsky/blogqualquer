package me.edurevsky.blog.blogqualquer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
// @EnableCaching
class BlogqualquerApplication

fun main(args: Array<String>) {
	runApplication<BlogqualquerApplication>(*args)
}
