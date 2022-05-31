package me.edurevsky.blog.blogqualquer.entities

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val name: String? = null
) : GrantedAuthority {

    override fun getAuthority(): String? = this.name
}
