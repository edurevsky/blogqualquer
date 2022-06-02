package me.edurevsky.blog.blogqualquer.mappers

import me.edurevsky.blog.blogqualquer.dto.PostView
import me.edurevsky.blog.blogqualquer.entities.Post
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter

@Component
class PostToPostViewMapper : Mapper<Post, PostView> {

   private val format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

    override fun map(data: Post): PostView {

        val formattedDate = data.lastDate?.format(format)

        return PostView(
            id = data.id,
            title = data.title,
            content = data.content,
            lastDate = formattedDate,
            authorCompleteName = data.author?.completeName
        )
    }
}