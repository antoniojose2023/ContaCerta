package br.com.devmobile.contacerta.data.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import br.com.devmobile.contacerta.data.entity.Categoria
import br.com.devmobile.contacerta.data.entity.Transacao

data class CategoriaComTransacao(
    @Embedded
    val categoria: Categoria,
    @Relation(
        parentColumn = "categoria_id",
        entityColumn = "categoria_id",
        entity = Transacao::class
    )
    val transacoes: List<Transacao>
)