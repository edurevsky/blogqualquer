package me.edurevsky.blog.blogqualquer.rest.controllers

import me.edurevsky.blog.blogqualquer.dto.CommentView
import me.edurevsky.blog.blogqualquer.dto.NewCommentRequest
import me.edurevsky.blog.blogqualquer.services.CommentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriBuilder
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/api/v1/comments")
class CommentRestController(
    private val commentService: CommentService
) {

    @PostMapping
    fun addComment(
        @RequestBody request: NewCommentRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<CommentView> {
        val commentView = commentService.addComment(request)
        val uri = uriBuilder.path("/api/v1/comments/${commentView.id}").build().toUri()
        return ResponseEntity.created(uri).body(commentView)
    }
}