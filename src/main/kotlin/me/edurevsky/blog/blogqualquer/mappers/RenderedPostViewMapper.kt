package me.edurevsky.blog.blogqualquer.mappers

import me.edurevsky.blog.blogqualquer.dto.RenderedPostView
import me.edurevsky.blog.blogqualquer.entities.Post
import me.edurevsky.blog.blogqualquer.services.MarkdownService
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter

@Component
class RenderedPostViewMapper(
    private val markdownService: MarkdownService
) : Mapper<Post, RenderedPostView> {

    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

    override fun map(data: Post): RenderedPostView {
        val renderedContent = markdownService.render(data.content!!)
        val formattedDate = data.lastDate?.format(formatter)

        return RenderedPostView(
            title = data.title,
            content = renderedContent,
            lastDate = formattedDate,
            authorCompleteName = data.author?.completeName
        )
    }
}