package me.edurevsky.blog.blogqualquer.dto

data class ErrorView(
    val status: Int? = null,
    val error: String? = null,
    val message: String? = null,
    val path: String? = null
)
