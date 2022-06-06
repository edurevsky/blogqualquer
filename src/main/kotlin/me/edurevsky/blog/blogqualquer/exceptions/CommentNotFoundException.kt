package me.edurevsky.blog.blogqualquer.exceptions

import javax.persistence.EntityNotFoundException

class CommentNotFoundException(s: String) : EntityNotFoundException(s)
