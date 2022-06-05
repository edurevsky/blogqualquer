package me.edurevsky.blog.blogqualquer.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "posts")
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val title: String? = null,
    val content: String? = null,

    @Column(name = "release_date")
    val releaseDate: LocalDateTime? = null,

    @Column(name = "update_date")
    val updateDate: LocalDateTime? = null,

    @ManyToOne
    val author: User? = null,

    val about: String? = null
) {
    val lastDate: LocalDateTime?
        get() {
            return if (releaseDate?.isAfter(updateDate) == true) {
                 releaseDate
            } else {
                updateDate
            }
        }
}
