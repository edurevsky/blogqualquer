package me.edurevsky.blog.blogqualquer.rest.controllers

import me.edurevsky.blog.blogqualquer.dto.NewPostRequest
import me.edurevsky.blog.blogqualquer.dto.PostView
import me.edurevsky.blog.blogqualquer.dto.UpdatePostRequest
import me.edurevsky.blog.blogqualquer.services.PostService
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/api/v1/posts")
class PostRestController(
    private val postService: PostService
) {

    @PostMapping("/save")
    fun saveNewPost(
        @RequestBody postRequest: NewPostRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<PostView> {
        val postView = postService.saveNewPost(postRequest)
        val uri = uriBuilder.path("/api/v1/posts/${postView.id}").build().toUri()
        return ResponseEntity.created(uri).body(postView)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): PostView = postService.findById(id)

    @PutMapping("/update")
    @Transactional
    fun updatePost(@RequestBody request: UpdatePostRequest): PostView = postService.updatePost(request)
}