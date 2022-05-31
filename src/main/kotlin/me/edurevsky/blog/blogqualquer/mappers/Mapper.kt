package me.edurevsky.blog.blogqualquer.mappers

interface Mapper<In, Out> {

    fun map(data: In): Out
}