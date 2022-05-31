package me.edurevsky.blog.blogqualquer.services.implementation

import me.edurevsky.blog.blogqualquer.services.MarkdownService
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.springframework.stereotype.Service

@Service
class MarkdownServiceImpl : MarkdownService {

    override fun render(content: String): String {
        val parser = Parser.builder().build()
        val document = parser.parse(content)
        val renderer = HtmlRenderer.builder().build()
        return renderer.render(document)
    }
}