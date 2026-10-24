package br.com.devmobile.contacerta.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "categoria")
data class Categoria(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("categoria_id")
    val id: Long,
    val nome: String,
): Serializable
