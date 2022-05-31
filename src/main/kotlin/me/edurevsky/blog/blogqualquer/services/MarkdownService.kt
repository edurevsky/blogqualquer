package me.edurevsky.blog.blogqualquer.services

interface MarkdownService {

    fun render(content: String): String
}