package me.edurevsky.blog.blogqualquer.mappers

import me.edurevsky.blog.blogqualquer.dto.NewCommentRequest
import me.edurevsky.blog.blogqualquer.entities.Comment
import me.edurevsky.blog.blogqualquer.services.PostService
import me.edurevsky.blog.blogqualquer.services.UserService
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class NewCommentRequestMapper(
    private val postService: PostService,
    private val userService: UserService
) : Mapper<NewCommentRequest, Comment> {

    override fun map(data: NewCommentRequest): Comment {
        val post = postService.getPostById(data.postId!!)
        val author = userService.findById(data.authorId!!)
        val currentTime = LocalDateTime.now()

        return Comment(
            post = post,
            content = data.content,
            author = author,
            createdAt = currentTime,
            updatedAt = currentTime,
        )
    }
}