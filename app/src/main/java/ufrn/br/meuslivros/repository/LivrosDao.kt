package ufrn.br.meuslivros.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ufrn.br.meuslivros.LivroViewModel
import ufrn.br.meuslivros.domain.Livros

@Dao
interface LivrosDao {

    @Insert
    fun inserirLivro(livro: Livros)

    @Query("SELECT * FROM Livros")
    fun listarTodos() : List<Livros>

}