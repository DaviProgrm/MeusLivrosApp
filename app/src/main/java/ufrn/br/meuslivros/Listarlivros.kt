package ufrn.br.meuslivros

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ufrn.br.meuslivros.database.AppDatabase
import ufrn.br.meuslivros.databinding.ActivityListarlivrosBinding
import ufrn.br.meuslivros.domain.Livros
import ufrn.br.meuslivros.repository.LivrosDao

class Listarlivros : AppCompatActivity() {

    private lateinit var binding: ActivityListarlivrosBinding
    private lateinit var livrosDao: LivrosDao
    private var livroIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_listarlivros)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "livros-db").build()
        livrosDao = db.livrosDao()

        // Chame a função para listar os livros
        listarLivros()
        binding.button5.setOnClickListener {
            if (livroIndex > 0) {
                livroIndex--
                listarLivros()
            }
        }

        binding.button6.setOnClickListener {
                livroIndex++
                listarLivros()
        }

    }

    private fun listarLivros() {
        lifecycleScope.launch(Dispatchers.IO) {
            val livros = livrosDao.listarTodos()

            // Atualize a interface do usuário na thread principal
            launch(Dispatchers.Main) {
                exibirListaDeLivros(livros)
            }
        }
    }

    private fun exibirListaDeLivros(livros: List<Livros>) {
        val tituloTextView = binding.textView12
        val autorTextView = binding.textView13
        val anoTextView = binding.textView14
        val notaTextView = binding.textView15
        val numeroPagina = binding.textView8
        val indexNovo = livroIndex + 1

        if (livros.isNotEmpty() && livroIndex >= 0 && livroIndex < livros.size) {
            val livro = livros[livroIndex]
            Log.d("livro", "variavel livro index: $livroIndex")
            // Atualize as TextViews com os atributos do livro
            tituloTextView.text = "Título: ${livro.titulo}"
            autorTextView.text = "Autor: ${livro.autor}"
            anoTextView.text = "Ano: ${livro.ano}"
            notaTextView.text = "Nota: ${livro.nota}"
            numeroPagina.text = indexNovo.toString()
        }
    }

}



