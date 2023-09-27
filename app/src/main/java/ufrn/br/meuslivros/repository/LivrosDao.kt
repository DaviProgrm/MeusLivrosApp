package ufrn.br.meuslivros.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ufrn.br.meuslivros.domain.Livros

@Dao
interface LivrosDao {

    @Insert
    fun inserirLivro(livrosDao: LivrosDao) : String

    @Query("SELECT * FROM Livros")
    fun listarTodos() : List<Livros>
}