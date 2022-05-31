package me.edurevsky.blog.blogqualquer.mappers

import me.edurevsky.blog.blogqualquer.dto.PostView
import me.edurevsky.blog.blogqualquer.entities.Post
import org.springframework.stereotype.Component

@Component
class PostToPostViewMapper : Mapper<Post, PostView> {

    override fun map(data: Post): PostView {
        return PostView(
            title = data.title,
            content = data.content,
            releaseDate = data.releaseDate,
            updateDate = data.updateDate
        )
    }
}