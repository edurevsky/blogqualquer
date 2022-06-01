package me.edurevsky.blog.blogqualquer.dto

import java.time.LocalDateTime

data class PostView(
    val id: Long? = null,
    val title: String? = null,
    var content: String? = null,
    val lastDate: LocalDateTime? = null,
    val authorCompleteName: String? = null
)
