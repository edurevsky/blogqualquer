package me.edurevsky.blog.blogqualquer.web.controllers

import me.edurevsky.blog.blogqualquer.services.MarkdownService
import me.edurevsky.blog.blogqualquer.services.PostService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/posts")
class PostController(
    private val postService: PostService,
    private val markdownService: MarkdownService
) {

    @GetMapping("/{id}")
    fun getPostById(@PathVariable("id") id: Long): ModelAndView {
        val mv = ModelAndView("post")
        val post = postService.findById(id)

        mv.addObject("post", post)
        mv.addObject("renderedContent", markdownService.render(post.content!!))
        return mv
    }
}