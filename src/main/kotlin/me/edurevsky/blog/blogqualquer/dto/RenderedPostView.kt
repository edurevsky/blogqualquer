package me.edurevsky.blog.blogqualquer.dto

data class RenderedPostView(
    val title: String? = null,
    val content: String? = null,
    val lastDate: String? = null,
    val authorCompleteName: String? = null,
    val comments: List<CommentView>? = null
)
