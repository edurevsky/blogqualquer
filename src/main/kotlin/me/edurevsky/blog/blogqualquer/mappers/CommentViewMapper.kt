package me.edurevsky.blog.blogqualquer.mappers

import me.edurevsky.blog.blogqualquer.dto.CommentView
import me.edurevsky.blog.blogqualquer.entities.Comment
import me.edurevsky.blog.blogqualquer.utils.DateTimeFormat.formatter
import org.springframework.stereotype.Component

@Component
class CommentViewMapper : Mapper<Comment, CommentView> {

    override fun map(data: Comment): CommentView {
        val lastModifiedDate = data.lastDate?.format(formatter)

        return CommentView(
            id = data.id,
            content = data.content,
            authorCompleteName = data.author?.completeName,
            lastDate = lastModifiedDate
        )
    }
}