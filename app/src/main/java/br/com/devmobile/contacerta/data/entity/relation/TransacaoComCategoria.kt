package br.com.devmobile.contacerta.data.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import br.com.devmobile.contacerta.data.entity.Categoria
import br.com.devmobile.contacerta.data.entity.Transacao
import kotlin.reflect.KClass

data class TransacaoComCategoria(
    @Embedded
      val transacao: Transacao,
      @Relation(
          parentColumn = "categoria_id",
          entityColumn = "categoria_id", 
          entity = Categoria::class
      )
      val categoria: Categoria
)