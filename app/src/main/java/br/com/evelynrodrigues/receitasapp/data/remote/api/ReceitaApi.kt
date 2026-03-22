package br.com.evelynrodrigues.receitasapp.data.remote.api

import br.com.evelynrodrigues.receitasapp.data.remote.dto.ReceitaDto
import retrofit2.http.GET

interface ReceitaApi {

    // Método para obter uma lista de receitas da API
    @GET("recipes")
    suspend fun getReceitas(): List<ReceitaDto>

}