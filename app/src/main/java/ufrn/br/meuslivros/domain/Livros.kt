package ufrn.br.meuslivros.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Livros (

    @PrimaryKey
    val id:Int,
    val nome:String,
    val autor:String,
    val ano:Int,
    val nota:Float
)
