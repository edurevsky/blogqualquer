package me.edurevsky.blog.blogqualquer.services.implementation

import me.edurevsky.blog.blogqualquer.dto.NewPostRequest
import me.edurevsky.blog.blogqualquer.dto.PostView
import me.edurevsky.blog.blogqualquer.dto.UpdatePostRequest
import me.edurevsky.blog.blogqualquer.entities.Post
import me.edurevsky.blog.blogqualquer.mappers.NewPostRequestToPostMapper
import me.edurevsky.blog.blogqualquer.mappers.PostToPostViewMapper
import me.edurevsky.blog.blogqualquer.repositories.PostRepository
import me.edurevsky.blog.blogqualquer.services.PostService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.persistence.EntityNotFoundException

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val postRequestToPostMapper: NewPostRequestToPostMapper,
    private val postToPostViewMapper: PostToPostViewMapper
) : PostService {

    override fun saveNewPost(request: NewPostRequest): PostView {
        val post = postRequestToPostMapper.map(request)
        val postEntity = postRepository.save(post)
        return postToPostViewMapper.map(postEntity)
    }

    override fun findById(id: Long): PostView {
        val postEntity = postRepository.findById(id)
            .orElseThrow { EntityNotFoundException("'$id' not found") }
        return postToPostViewMapper.map(postEntity)
    }

    override fun updatePost(request: UpdatePostRequest): PostView {
        val fds: Post = postRepository.findById(request.id!!).orElseThrow { EntityNotFoundException("Not found") }
        fds.updateDate = LocalDateTime.now()
        fds.title = request.title
        fds.content = request.content
        return postToPostViewMapper.map(fds)
    }
}