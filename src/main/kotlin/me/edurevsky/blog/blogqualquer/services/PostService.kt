package me.edurevsky.blog.blogqualquer.services

import me.edurevsky.blog.blogqualquer.dto.NewPostRequest
import me.edurevsky.blog.blogqualquer.dto.PostView
import me.edurevsky.blog.blogqualquer.dto.RenderedPostView
import me.edurevsky.blog.blogqualquer.dto.UpdatePostRequest
import me.edurevsky.blog.blogqualquer.entities.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostService {

    fun saveNewPost(request: NewPostRequest): PostView

    fun findById(id: Long): RenderedPostView

    fun getPostById(id: Long): Post

    fun updatePost(request: UpdatePostRequest): PostView

    fun deletePost(id: Long)

    fun findPaginated(pageable: Pageable): Page<PostView>

    fun findByTopicPaginated(topicName: String, pageable: Pageable): Page<PostView>
}