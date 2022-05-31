package me.edurevsky.blog.blogqualquer.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val username: String? = null,
    val password: String? = null,

    @Column(name = "complete_name")
    val completeName: String? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "roles_users")
    @JsonIgnore
    val roles: List<Role> = mutableListOf()
)
