package me.edurevsky.blog.blogqualquer.services

import me.edurevsky.blog.blogqualquer.dto.CommentView
import me.edurevsky.blog.blogqualquer.dto.NewCommentRequest

interface CommentService {

    fun findByPostId(id: Long): List<CommentView>

    fun addComment(comment: NewCommentRequest): CommentView
}