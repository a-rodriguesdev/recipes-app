package br.com.evelynrodrigues.receitasapp.ui.receita

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.evelynrodrigues.receitasapp.data.repository.ReceitaRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ReceitaViewModel(
    private val repository: ReceitaRepository
): ViewModel() {

    val receitas = repository.receitas.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(
            stopTimeoutMillis = 5_000
        ),
        initialValue = emptyList()
    )

    init {
        atualizar()
    }

    fun atualizar() {
        viewModelScope.launch {
            repository.atualizarReceitas()
        }
    }

}