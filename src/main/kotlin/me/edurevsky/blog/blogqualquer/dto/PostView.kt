package me.edurevsky.blog.blogqualquer.dto

data class PostView(
    val id: Long? = null,
    val title: String? = null,
    val content: String? = null,
    val lastDate: String? = null,
    val authorCompleteName: String? = null,
    val about: String? = null
)
