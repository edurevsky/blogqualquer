package me.edurevsky.blog.blogqualquer.mappers

import java.time.format.DateTimeFormatter

object DateTimeFormat {

    val formatter: DateTimeFormatter
        get() = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
}