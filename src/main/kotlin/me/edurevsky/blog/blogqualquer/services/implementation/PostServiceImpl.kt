package me.edurevsky.blog.blogqualquer.services.implementation

import me.edurevsky.blog.blogqualquer.dto.NewPostRequest
import me.edurevsky.blog.blogqualquer.dto.PostView
import me.edurevsky.blog.blogqualquer.dto.RenderedPostView
import me.edurevsky.blog.blogqualquer.dto.UpdatePostRequest
import me.edurevsky.blog.blogqualquer.entities.Post
import me.edurevsky.blog.blogqualquer.exceptions.PostNotFoundException
import me.edurevsky.blog.blogqualquer.mappers.NewPostRequestToPostMapper
import me.edurevsky.blog.blogqualquer.mappers.PostToPostViewMapper
import me.edurevsky.blog.blogqualquer.mappers.RenderedPostViewMapper
import me.edurevsky.blog.blogqualquer.repositories.PostRepository
import me.edurevsky.blog.blogqualquer.services.PostService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val postRequestToPostMapper: NewPostRequestToPostMapper,
    private val postToPostViewMapper: PostToPostViewMapper,
    private val renderedPostViewMapper: RenderedPostViewMapper
) : PostService {

    override fun saveNewPost(request: NewPostRequest): PostView {
        val post = postRequestToPostMapper.map(request)
        val postEntity = postRepository.save(post)
        return postToPostViewMapper.map(postEntity)
    }

    override fun findById(id: Long): RenderedPostView {
        val postEntity = postRepository.findById(id)
            .orElseThrow { PostNotFoundException("'$id' not found") }
        return renderedPostViewMapper.map(postEntity)
    }

    override fun getPostById(id: Long): Post {
        return postRepository.findById(id).orElseThrow { PostNotFoundException("$id not found") }
    }

    override fun updatePost(request: UpdatePostRequest): PostView {
        val post: Post = postRepository.findById(request.id!!).orElseThrow { PostNotFoundException("Not found") }
        val updatedPost = post.copy(
            updateDate = LocalDateTime.now(),
            title = request.title,
            content = request.content,
            about = request.about
        )
        postRepository.save(updatedPost)
        return postToPostViewMapper.map(updatedPost)
    }

    override fun deletePost(id: Long) {
        val post = postRepository.findById(id).orElseThrow { PostNotFoundException("Not found") }
        postRepository.delete(post)
    }

    override fun findPaginated(pageable: Pageable): Page<PostView> {
        val posts = postRepository.findAll(pageable)
        return posts.map { post -> postToPostViewMapper.map(post) }
    }
}