package me.edurevsky.blog.blogqualquer.mappers

import me.edurevsky.blog.blogqualquer.dto.PostView
import me.edurevsky.blog.blogqualquer.entities.Post
import me.edurevsky.blog.blogqualquer.mappers.DateTimeFormat.formatter
import org.springframework.stereotype.Component

@Component
class PostToPostViewMapper : Mapper<Post, PostView> {

    override fun map(data: Post): PostView {
        val formattedDate = data.lastDate?.format(formatter)

        return PostView(
            id = data.id,
            title = data.title,
            content = data.content,
            lastDate = formattedDate,
            authorCompleteName = data.author?.completeName
        )
    }
}