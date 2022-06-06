package me.edurevsky.blog.blogqualquer.mappers

import me.edurevsky.blog.blogqualquer.dto.NewCommentRequest
import me.edurevsky.blog.blogqualquer.entities.Comment
import me.edurevsky.blog.blogqualquer.services.PostService
import org.springframework.stereotype.Component

@Component
class NewCommentRequestMapper(
    private val postService: PostService
) : Mapper<NewCommentRequest, Comment> {

    override fun map(data: NewCommentRequest): Comment {
        val post = postService.getPostById(data.postId!!)
        return Comment(
            post = post,
            content = data.content
        )
    }
}