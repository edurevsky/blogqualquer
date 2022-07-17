package me.edurevsky.blog.blogqualquer.entities

import me.edurevsky.blog.blogqualquer.utils.LastModifiedDate.getNewerDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "posts")
data class Post(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var title: String? = null,
    var content: String? = null,

    @Column(name = "release_date")
    val releaseDate: LocalDateTime? = null,

    @Column(name = "update_date")
    var updateDate: LocalDateTime? = null,

    @ManyToOne
    @JoinColumn(name = "author_id")
    val author: User? = null,

    var about: String? = null,

    @OneToMany(mappedBy = "post")
    val comments: List<Comment> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "topic_id")
    val topic: Topic? = null,

    @Column(name = "open")
    val isOpen: Boolean? = null
) {

    val lastDate: LocalDateTime?
        get() = getNewerDate(releaseDate, updateDate)

    fun update(title: String, content: String, about: String) {
        this.updateDate = LocalDateTime.now()
        this.title = title
        this.content = content
        this.about = about
    }
}