package me.edurevsky.blog.blogqualquer.dto

import com.fasterxml.jackson.annotation.JsonFormat
import me.edurevsky.blog.blogqualquer.utils.DateTimeFormat.formatter
import java.time.LocalDateTime

data class PostView(
    val id: Long? = null,
    val title: String? = null,
    val content: String? = null,
    @JsonFormat(pattern = formatter)
    val lastDate: LocalDateTime? = null,
    val authorCompleteName: String? = null,
    val about: String? = null,
    val comments: List<CommentView>? = null,
    val isOpen: Boolean? = null
)
