package ufrn.br.meuslivros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ufrn.br.meuslivros.database.AppDatabase
import ufrn.br.meuslivros.databinding.ActivityCadastrarBinding
import ufrn.br.meuslivros.repository.LivrosDao
import ufrn.br.meuslivros.domain.Livros
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import android.os.Handler


class Cadastrar : AppCompatActivity() {

    lateinit var binding: ActivityCadastrarBinding
    lateinit var viewModel: LivroViewModel
    lateinit var livrosDao: LivrosDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cadastrar)
        viewModel = ViewModelProvider(this)[LivroViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.button4.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Inicialize a instância do banco de dados (Room)
        val db =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "livros-db").build()
        livrosDao = db.livrosDao()

        // Chame a função configuraBotaoSalvar aqui
        configuraBotaoSalvar()
    }

    // Mova a função configuraBotaoSalvar para fora do método onCreate
    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.button3

        botaoSalvar.setOnClickListener {
            // Use lifecycleScope.launch para iniciar uma coroutine em Dispatchers.IO
            lifecycleScope.launch(Dispatchers.IO) {
                val titulo = binding.editTextText.text.toString()
                val autor = binding.editTextText2.text.toString()
                val ano = binding.editTextText3.text.toString().toInt()
                val nota = binding.ratingBar.rating

                val livroNovo = Livros(
                    titulo = titulo,
                    autor = autor,
                    ano = ano,
                    nota = nota
                )
                livrosDao.inserirLivro(livroNovo)

            }
            val snackbar = Snackbar.make(binding.button3, "Livro cadastrado com sucesso!", 5000) // Define a duração em milissegundos
            snackbar.show()

            Handler(Looper.getMainLooper()).postDelayed({

                finish()
            }, 1000) // Define o mesmo tempo de duração aqui (em milissegundos)
        }

        }
    }

