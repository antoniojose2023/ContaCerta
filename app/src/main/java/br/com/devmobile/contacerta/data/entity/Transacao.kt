package br.com.devmobile.contacerta.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo

@Entity(
     tableName = "transacao",
     foreignKeys = [ForeignKey(
         entity = Categoria::class,
         parentColumns = ["categoria_id"],
         childColumns = ["categoria_id"]
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
)
