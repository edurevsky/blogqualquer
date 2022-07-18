package me.edurevsky.blog.blogqualquer.mappers

import me.edurevsky.blog.blogqualquer.dto.RenderedPostView
import me.edurevsky.blog.blogqualquer.entities.Post
import me.edurevsky.blog.blogqualquer.services.MarkdownService
import org.springframework.stereotype.Component

@Component
class RenderedPostViewMapper(
    private val markdownService: MarkdownService,
    private val commentViewMapper: CommentViewMapper
) : Mapper<Post, RenderedPostView> {

    override fun map(data: Post): RenderedPostView {
        val renderedContent = markdownService.render(data.content!!)
        val comments = data.comments.map { commentViewMapper.map(it) }

        return RenderedPostView(
            title = data.title,
            content = renderedContent,
            lastDate = data.lastDate,
            authorCompleteName = data.author?.completeName,
            comments = comments
        )
    }
}