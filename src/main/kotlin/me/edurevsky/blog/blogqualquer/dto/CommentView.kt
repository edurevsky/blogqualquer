package me.edurevsky.blog.blogqualquer.dto

data class CommentView(
    val id: Long? = null,
    val content: String? = null,
    val authorCompleteName: String? = null,
    val lastDate: String? = null
)
