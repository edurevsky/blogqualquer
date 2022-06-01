package me.edurevsky.blog.blogqualquer.rest.controllers

import me.edurevsky.blog.blogqualquer.dto.NewPostRequest
import me.edurevsky.blog.blogqualquer.dto.PostView
import me.edurevsky.blog.blogqualquer.dto.UpdatePostRequest
import me.edurevsky.blog.blogqualquer.services.MarkdownService
import me.edurevsky.blog.blogqualquer.services.PostService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/posts")
class PostRestController(
    private val postService: PostService,
    private val markdownService: MarkdownService
) {

    @PostMapping("/save")
    fun saveNewPost(
        @Valid @RequestBody postRequest: NewPostRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<PostView> {
        val postView = postService.saveNewPost(postRequest)
        val uri = uriBuilder.path("/api/v1/posts/${postView.id}").build().toUri()
        return ResponseEntity.created(uri).body(postView)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<PostView> {
        val post = postService.findById(id)
        return ResponseEntity.ok(post)
    }

    @GetMapping("/rendered/{id}")
    fun getPostWithRenderedMdContentById(@PathVariable("id") id: Long): ResponseEntity<PostView> {
        val post = postService.findById(id)
        post.content = markdownService.render(post.content!!)
        return ResponseEntity.ok(post)
    }

    @PutMapping("/update")
    @Transactional
    fun updatePost(@Valid @RequestBody request: UpdatePostRequest): PostView = postService.updatePost(request)

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable("id") id: Long): ResponseEntity<Any> {
        postService.deletePost(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping
    fun findPaginated(@PageableDefault(size = 10, sort = ["updateDate"]) pageable: Pageable) = postService.findPaginated(pageable)
}