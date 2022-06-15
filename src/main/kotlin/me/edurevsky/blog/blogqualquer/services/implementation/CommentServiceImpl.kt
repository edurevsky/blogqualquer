package me.edurevsky.blog.blogqualquer.services.implementation

import me.edurevsky.blog.blogqualquer.dto.CommentView
import me.edurevsky.blog.blogqualquer.dto.NewCommentRequest
import me.edurevsky.blog.blogqualquer.exceptions.ClosedPostException
import me.edurevsky.blog.blogqualquer.exceptions.CommentNotFoundException
import me.edurevsky.blog.blogqualquer.mappers.CommentViewMapper
import me.edurevsky.blog.blogqualquer.mappers.NewCommentRequestMapper
import me.edurevsky.blog.blogqualquer.repositories.CommentRepository
import me.edurevsky.blog.blogqualquer.services.CommentService
import me.edurevsky.blog.blogqualquer.services.PostService
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val commentViewMapper: CommentViewMapper,
    private val newCommentRequestMapper: NewCommentRequestMapper,
    private val postService: PostService
) : CommentService {

    override fun findByPostId(id: Long): List<CommentView> {
        val comments = commentRepository.findByPostId(id)
        return comments.map { c ->  commentViewMapper.map(c) }
    }

    override fun addComment(comment: NewCommentRequest): CommentView {
        val post = postService.getPostById(comment.postId!!)
        if (!post.isOpen!!) {
            throw ClosedPostException()
        }
        val mappedComment = newCommentRequestMapper.map(comment)
        val saved = commentRepository.save(mappedComment)
        return commentViewMapper.map(saved)
    }

    override fun deleteComment(id: Long) {
        val comment = commentRepository.findById(id).orElseThrow { CommentNotFoundException("$id not found.") }
        commentRepository.delete(comment)
    }
}