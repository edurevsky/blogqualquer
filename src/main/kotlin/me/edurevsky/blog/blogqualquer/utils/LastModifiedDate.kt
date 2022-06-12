package me.edurevsky.blog.blogqualquer.utils

import java.time.LocalDateTime

object LastModifiedDate {

    fun getNewerDate(first: LocalDateTime?, last: LocalDateTime?): LocalDateTime? {
        return if (first?.isAfter(last) == true) {
            first
        } else {
            last
        }
    }
}