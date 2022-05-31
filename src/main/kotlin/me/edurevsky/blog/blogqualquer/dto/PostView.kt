package me.edurevsky.blog.blogqualquer.dto

import java.time.LocalDateTime

data class PostView(
    val title: String? = null,
    val content: String? = null,
    val releaseDate: LocalDateTime? = null,
    val updateDate: LocalDateTime? = null
)
