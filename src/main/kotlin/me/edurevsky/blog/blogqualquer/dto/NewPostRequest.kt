package me.edurevsky.blog.blogqualquer.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class NewPostRequest(
    @field:NotBlank(message = "O título da postagem é obrigatório.")
    val title: String? = null,
    @field:NotBlank(message = "Não é possível enviar um post sem conteúdo.")
    val content: String? = null,
    @field:NotNull
    val authorId: Long? = null
)
