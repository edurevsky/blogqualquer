package me.edurevsky.blog.blogqualquer.mappers

import me.edurevsky.blog.blogqualquer.dto.PostView
import me.edurevsky.blog.blogqualquer.entities.Post
import org.springframework.stereotype.Component

@Component
class PostToPostViewMapper : Mapper<Post, PostView> {

    override fun map(data: Post): PostView {
        val lastModificationDate = if (data.updateDate?.isAfter(data.releaseDate) == true) {
            data.updateDate
        } else {
            data.releaseDate
        }

        return PostView(
            id = data.id,
            title = data.title,
            content = data.content,
            lastDate = lastModificationDate,
            authorCompleteName = data.author?.completeName
        )
    }
}