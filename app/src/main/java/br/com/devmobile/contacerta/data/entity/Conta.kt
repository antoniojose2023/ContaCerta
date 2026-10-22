package br.com.devmobile.contacerta.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conta")
data class Conta (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val nome: String
)