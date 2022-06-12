package me.edurevsky.blog.blogqualquer.services

import me.edurevsky.blog.blogqualquer.entities.Topic

interface TopicService {

    fun findById(id: Long): Topic
}