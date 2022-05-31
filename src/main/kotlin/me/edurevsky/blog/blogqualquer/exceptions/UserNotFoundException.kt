package me.edurevsky.blog.blogqualquer.exceptions

import javax.persistence.EntityNotFoundException

class UserNotFoundException(s: String) : EntityNotFoundException(s)
