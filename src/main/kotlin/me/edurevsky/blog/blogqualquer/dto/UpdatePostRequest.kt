package me.edurevsky.blog.blogqualquer.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UpdatePostRequest(
    @field:NotNull
    val id: Long? = null,
    @field:NotBlank(message = "O título é obrigatório.")
    val title: String? = null,
    @field:NotBlank(message = "O conteúdo não pode estar em branco.")
    val content: String? = null,
    @field:NotBlank(message = "Sobre não pode estar em branco.")
    val about: String? = null
)