package me.edurevsky.blog.blogqualquer.entities

import me.edurevsky.blog.blogqualquer.entities.LastModifiedDate.getNewerDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "comments")
data class Comment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "post_id")
    val post: Post? = null,
    val content: String? = null,

    @ManyToOne
    @JoinColumn(name = "author_id")
    val author: User? = null,

    @Column(name = "created_at")
    val createdAt: LocalDateTime? = null,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null
) {

    val lastDate: LocalDateTime?
        get() = getNewerDate(createdAt, updatedAt)
}
