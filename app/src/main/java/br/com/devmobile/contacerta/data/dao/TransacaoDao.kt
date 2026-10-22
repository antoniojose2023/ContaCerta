package br.com.devmobile.contacerta.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.devmobile.contacerta.data.entity.Conta
import br.com.devmobile.contacerta.data.entity.Transacao
import br.com.devmobile.contacerta.data.entity.relation.TransacaoComCategoria

@Dao
interface TransacaoDao {

    @Insert
    suspend fun salvar(transacao: Transacao): Long

    @Update
    suspend fun atualizar(transacao: Transacao): Int

    @Delete
    suspend fun delete(transacao: Transacao): Int

    @Query("SELECT * FROM transacao")
    suspend fun listar(): List<Transacao>

    @Query("SELECT * FROM transacao ORDER BY categoria_id DESC")
    suspend fun listarTransacaoComCategoria(): List<TransacaoComCategoria>

    @Query("SELECT SUM(valor) FROM transacao WHERE tipo = :tipo")
    suspend fun sumValorReceitaOuDespesa(tipo: String): Double

    @Query("SELECT * FROM transacao WHERE tipo = :tipo ORDER BY categoria_id DESC")
    suspend fun getReceitaOuDespesa(tipo: String): List<TransacaoComCategoria>

    @Query("SELECT * FROM transacao WHERE nome LIKE '%' || :nomeTransaca || '%' ORDER BY categoria_id DESC")
    suspend fun listarTransacaoPorNome(nomeTransaca: String): List<TransacaoComCategoria>
}