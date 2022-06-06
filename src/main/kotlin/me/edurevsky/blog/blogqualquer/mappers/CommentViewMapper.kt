package me.edurevsky.blog.blogqualquer.mappers

import me.edurevsky.blog.blogqualquer.dto.CommentView
import me.edurevsky.blog.blogqualquer.entities.Comment
import org.springframework.stereotype.Component

@Component
class CommentViewMapper : Mapper<Comment, CommentView> {

    override fun map(data: Comment): CommentView {
        return CommentView(
            id = data.id,
            content = data.content
        )
    }
}