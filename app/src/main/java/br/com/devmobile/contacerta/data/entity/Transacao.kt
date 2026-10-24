package br.com.devmobile.contacerta.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo
import java.io.Serializable

@Entity(
     tableName = "transacao",
     foreignKeys = [ForeignKey(
         entity = Categoria::class,
         parentColumns = ["categoria_id"],
         childColumns = ["categoria_id"],
         //onDelete =  CASCADE,
         //onUpdate = CASCADE
     )]
)
data class Transacao(
     @PrimaryKey(autoGenerate = true)
     val id: Long,
     val nome: String,
     val descricao: String,
     val valor: Double,
     val tipo: String,
     val data: String,
     @ColumnInfo("categoria_id")
     val categoriaId: Long,
     val conta: String,
     @ColumnInfo("forma_pagamento", defaultValue = "CARTAO")
     val formaPagamento: String = "CARTAO"
): Serializable {
     constructor(id: Long, nome: String, valor: Double) : this(id = 0, nome = nome, descricao = "", valor = valor, tipo = "", data = "", categoriaId = 0, conta = "")
}
