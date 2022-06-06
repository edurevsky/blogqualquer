package me.edurevsky.blog.blogqualquer.rest.controllers

import me.edurevsky.blog.blogqualquer.dto.NewPostRequest
import me.edurevsky.blog.blogqualquer.dto.PostView
import me.edurevsky.blog.blogqualquer.dto.RenderedPostView
import me.edurevsky.blog.blogqualquer.dto.UpdatePostRequest
import me.edurevsky.blog.blogqualquer.services.PostService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/posts")
class PostRestController(
    private val postService: PostService,
) {

    @CacheEvict(value = ["singlePost", "paginatedPosts"], allEntries = true)
    @PostMapping
    fun saveNewPost(
        @Valid @RequestBody postRequest: NewPostRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<PostView> {
        val postView = postService.saveNewPost(postRequest)
        val uri = uriBuilder.path("/api/v1/posts/${postView.id}").build().toUri()
        return ResponseEntity.created(uri).body(postView)
    }

    @Cacheable("singlePost")
    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<RenderedPostView> {
        val post = postService.findById(id)
        return ResponseEntity.ok(post)
    }

    @CacheEvict(value = ["singlePost", "paginatedPosts"], allEntries = true)
    @PutMapping
    @Transactional
    fun updatePost(@Valid @RequestBody request: UpdatePostRequest): PostView = postService.updatePost(request)

    @CacheEvict(value = ["singlePost", "paginatedPosts"], allEntries = true)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(@PathVariable("id") id: Long) = postService.deletePost(id)

    @Cacheable("paginatedPosts")
    @GetMapping
    fun findPaginated(@PageableDefault(size = 10, sort = ["updateDate"]) pageable: Pageable) = postService.findPaginated(pageable)
}