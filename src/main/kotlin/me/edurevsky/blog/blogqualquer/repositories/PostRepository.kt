package me.edurevsky.blog.blogqualquer.repositories

import me.edurevsky.blog.blogqualquer.entities.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> {

    fun findByTopicName(topicName: String, pageable: Pageable): Page<Post>
}