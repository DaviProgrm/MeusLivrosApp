package ufrn.br.meuslivros.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ufrn.br.meuslivros.domain.Livros
import ufrn.br.meuslivros.repository.LivrosDao

@Database(entities = [Livros::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun livrosDao() : LivrosDao
}