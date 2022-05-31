package me.edurevsky.blog.blogqualquer.repositories

import me.edurevsky.blog.blogqualquer.entities.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long>