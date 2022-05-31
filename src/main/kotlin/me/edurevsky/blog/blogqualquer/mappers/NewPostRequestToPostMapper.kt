package me.edurevsky.blog.blogqualquer.mappers

import me.edurevsky.blog.blogqualquer.dto.NewPostRequest
import me.edurevsky.blog.blogqualquer.entities.Post
import java.time.LocalDateTime

class NewPostRequestToPostMapper : Mapper<NewPostRequest, Post> {

    override fun map(data: NewPostRequest): Post {
        val currentDateTime = LocalDateTime.now()
        return Post(
            title = data.title,
            content = data.content,
            releaseDate = currentDateTime,
            updateDate = currentDateTime
        )
    }
}