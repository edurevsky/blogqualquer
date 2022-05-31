package me.edurevsky.blog.blogqualquer.services

import me.edurevsky.blog.blogqualquer.dto.NewPostRequest
import me.edurevsky.blog.blogqualquer.dto.PostView

interface PostService {

    fun saveNewPost(request: NewPostRequest): PostView

    fun findById(id: Long): PostView
}