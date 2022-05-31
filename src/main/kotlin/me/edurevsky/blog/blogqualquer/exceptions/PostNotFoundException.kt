package me.edurevsky.blog.blogqualquer.exceptions

import javax.persistence.EntityNotFoundException

class PostNotFoundException(s: String) : EntityNotFoundException(s)
