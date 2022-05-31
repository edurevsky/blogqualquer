package me.edurevsky.blog.blogqualquer.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "posts")
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var title: String? = null,
    var content: String? = null,

    @Column(name = "release_date")
    val releaseDate: LocalDateTime? = null,

    @Column(name = "update_date")
    var updateDate: LocalDateTime? = null,

    @ManyToOne
    val author: User? = null
)
