package me.edurevsky.blog.blogqualquer.dto

import com.fasterxml.jackson.annotation.JsonFormat
import me.edurevsky.blog.blogqualquer.utils.DateTimeFormat.formatter
import java.time.LocalDateTime

data class CommentView(
    val id: Long? = null,
    val content: String? = null,
    val authorCompleteName: String? = null,
    @JsonFormat(pattern = formatter)
    val lastDate: LocalDateTime? = null
)
