package br.com.evelynrodrigues.receitasapp.data.repository

import br.com.evelynrodrigues.receitasapp.data.local.dao.ReceitaDao
import br.com.evelynrodrigues.receitasapp.data.mapper.toEntity
import br.com.evelynrodrigues.receitasapp.data.remote.api.ReceitaApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReceitaRepository(
    private val api: ReceitaApi,
    private val dao: ReceitaDao
) {

    val receitas = dao.getReceitas()

    suspend fun atualizarReceitas() {
        withContext(Dispatchers.IO) {
            try {
                val remoteReceitas = api.getReceitas()
                dao.insertAll(
                    receitas = remoteReceitas.map { receitaDto ->
                        receitaDto.toEntity()
                    })
            } catch (e: Exception) {
                // Falhou internet → mantém cache
            }
        }
    }
}