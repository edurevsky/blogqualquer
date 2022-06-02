package me.edurevsky.blog.blogqualquer.services

import me.edurevsky.blog.blogqualquer.dto.NewPostRequest
import me.edurevsky.blog.blogqualquer.dto.PostView
import me.edurevsky.blog.blogqualquer.dto.RenderedPostView
import me.edurevsky.blog.blogqualquer.dto.UpdatePostRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostService {

    fun saveNewPost(request: NewPostRequest): PostView

    fun findById(id: Long): RenderedPostView

    fun updatePost(request: UpdatePostRequest): PostView

    fun deletePost(id: Long)

    fun findPaginated(pageable: Pageable): Page<PostView>
}