package me.edurevsky.blog.blogqualquer.dto

data class NewCommentRequest(
    val postId: Long? = null,
    val authorId: Long? = null,
    val content: String? = null
)
