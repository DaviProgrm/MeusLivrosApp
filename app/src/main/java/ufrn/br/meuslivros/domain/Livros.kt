package ufrn.br.meuslivros.domain

import androidx.room.Entity

@Entity
data class Livros (
    val nome:String,
    val autor:String,
    val ano:Int,
    val nota:Float
)
