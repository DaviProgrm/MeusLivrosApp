package ufrn.br.meuslivros.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "livros")
data class Livros (

    @PrimaryKey(autoGenerate = true)
    val id:Long = 0,
    val titulo:String,
    val autor:String,
    val ano:Int,
    val nota:Float
)
