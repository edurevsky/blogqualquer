package me.edurevsky.blog.blogqualquer.services

import me.edurevsky.blog.blogqualquer.dto.NewPostRequest
import me.edurevsky.blog.blogqualquer.dto.PostView
import me.edurevsky.blog.blogqualquer.dto.UpdatePostRequest

interface PostService {

    fun saveNewPost(request: NewPostRequest): PostView

    fun findById(id: Long): PostView

    fun updatePost(request: UpdatePostRequest): PostView
}