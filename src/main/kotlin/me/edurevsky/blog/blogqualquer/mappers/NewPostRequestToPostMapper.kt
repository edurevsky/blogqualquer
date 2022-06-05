package me.edurevsky.blog.blogqualquer.mappers

import me.edurevsky.blog.blogqualquer.dto.NewPostRequest
import me.edurevsky.blog.blogqualquer.entities.Post
import me.edurevsky.blog.blogqualquer.services.UserService
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class NewPostRequestToPostMapper(
    private val userService: UserService
) : Mapper<NewPostRequest, Post> {

    override fun map(data: NewPostRequest): Post {
        val currentDateTime = LocalDateTime.now()
        val author = userService.findById(data.authorId!!)
        return Post(
            title = data.title,
            content = data.content,
            releaseDate = currentDateTime,
            updateDate = currentDateTime,
            author = author,
            about = data.about
        )
    }
}