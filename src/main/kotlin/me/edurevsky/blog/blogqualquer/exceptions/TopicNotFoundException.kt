package me.edurevsky.blog.blogqualquer.exceptions

import javax.persistence.EntityNotFoundException

class TopicNotFoundException(s: String) : EntityNotFoundException(s)
