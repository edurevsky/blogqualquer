package me.edurevsky.blog.blogqualquer.mappers

import me.edurevsky.blog.blogqualquer.dto.PostView
import me.edurevsky.blog.blogqualquer.entities.Post
import org.springframework.stereotype.Component

@Component
class PostToPostViewMapper(
    private val commentViewMapper: CommentViewMapper
) : Mapper<Post, PostView> {

    override fun map(data: Post): PostView {
        val comments = data.comments.map { commentViewMapper.map(it) }
        return PostView(
            id = data.id,
            title = data.title,
            content = data.content,
            lastDate = data.lastDate,
            authorCompleteName = data.author?.completeName,
            about = data.about,
            comments = comments,
            isOpen = data.isOpen
        )
    }
}