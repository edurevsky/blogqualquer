package me.edurevsky.blog.blogqualquer.repositories

import me.edurevsky.blog.blogqualquer.entities.Topic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TopicRepository : JpaRepository<Topic, Long>
