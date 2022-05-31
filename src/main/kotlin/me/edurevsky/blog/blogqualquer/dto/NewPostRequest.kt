package me.edurevsky.blog.blogqualquer.dto

import javax.validation.constraints.NotBlank

data class NewPostRequest(
    @field:NotBlank(message = "O título da postagem é obrigatório.")
    val title: String? = null,
    @field:NotBlank(message = "Não é possível enviar um post sem conteúdo.")
    val content: String? = null
)
