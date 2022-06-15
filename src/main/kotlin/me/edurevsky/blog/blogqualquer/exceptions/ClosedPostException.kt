package me.edurevsky.blog.blogqualquer.exceptions

class ClosedPostException(s: String = "Closed posts are unavailable to receive new comments.")
    : RuntimeException(s)
