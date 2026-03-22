package br.com.evelynrodrigues.receitasapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import br.com.evelynrodrigues.receitasapp.data.local.database.AppDatabase
import br.com.evelynrodrigues.receitasapp.data.remote.api.RetrofitClient
import br.com.evelynrodrigues.receitasapp.data.repository.ReceitaRepository
import br.com.evelynrodrigues.receitasapp.ui.receita.ReceitaListScreen
import br.com.evelynrodrigues.receitasapp.ui.receita.ReceitaViewModel
import br.com.evelynrodrigues.receitasapp.ui.receita.ReceitaViewModelFactory
import br.com.evelynrodrigues.receitasapp.ui.theme.ReceitasAppTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {

    private val api by lazy {
        RetrofitClient().getReceitaApi()
    }

    private val dao by lazy {
        AppDatabase.getDatabase(applicationContext).receitaDao()
    }

    private val repository by lazy {
        ReceitaRepository(api, dao)
    }

    private val viewModel: ReceitaViewModel by viewModels {
        ReceitaViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReceitasAppTheme {
                ReceitaListScreen(viewModel)
            }
        }
    }
}