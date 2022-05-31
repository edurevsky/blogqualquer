package me.edurevsky.blog.blogqualquer.dto

data class UpdatePostRequest(
    val id: Long? = null,
    val title: String? = null,
    val content: String? = null
)