package br.com.evelynrodrigues.receitasapp.data.mapper

import br.com.evelynrodrigues.receitasapp.data.local.entity.ReceitaEntity
import br.com.evelynrodrigues.receitasapp.data.remote.dto.ReceitaDto

fun ReceitaDto.toEntity() = ReceitaEntity(
    id = id,
    title = title,
    description = description,
    url = url
)