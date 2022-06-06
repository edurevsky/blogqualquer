package me.edurevsky.blog.blogqualquer.entities

import javax.persistence.*

@Entity
@Table(name = "comments")
data class Comment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "post_id")
    val post: Post? = null,
    val content: String? = null
)
