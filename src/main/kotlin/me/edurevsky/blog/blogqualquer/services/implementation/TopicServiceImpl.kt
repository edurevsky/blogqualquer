package me.edurevsky.blog.blogqualquer.services.implementation

import me.edurevsky.blog.blogqualquer.entities.Topic
import me.edurevsky.blog.blogqualquer.exceptions.TopicNotFoundException
import me.edurevsky.blog.blogqualquer.repositories.TopicRepository
import me.edurevsky.blog.blogqualquer.services.TopicService
import org.springframework.stereotype.Service

@Service
class TopicServiceImpl(
    private val topicRepository: TopicRepository
) : TopicService {

    override fun findById(id: Long): Topic {
        return topicRepository.findById(id).orElseThrow { TopicNotFoundException("Topic $id not found") }
    }
}