package me.edurevsky.blog.blogqualquer.entities

import me.edurevsky.blog.blogqualquer.utils.LastModifiedDate.getNewerDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "posts")
data class Post(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val title: String? = null,
    val content: String? = null,

    @Column(name = "release_date")
    val releaseDate: LocalDateTime? = null,

    @Column(name = "update_date")
    val updateDate: LocalDateTime? = null,

    @ManyToOne
    @JoinColumn(name = "author_id")
    val author: User? = null,

    val about: String? = null,

    @OneToMany(mappedBy = "post")
    val comments: List<Comment> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "topic_id")
    val topic: Topic? = null
) {

    val lastDate: LocalDateTime?
        get() = getNewerDate(releaseDate, updateDate)
}