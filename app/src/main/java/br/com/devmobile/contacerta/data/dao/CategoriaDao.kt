package br.com.devmobile.contacerta.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import br.com.devmobile.contacerta.data.entity.Categoria
import br.com.devmobile.contacerta.data.entity.Conta
import br.com.devmobile.contacerta.data.entity.relation.CategoriaComTransacao

@Dao
interface CategoriaDao {
    @Insert
    suspend fun salvar(categoria: Categoria): Long

    @Update
    suspend fun atualizar(categoria: Categoria): Int

    @Delete
    suspend fun delete(categoria: Categoria): Int

    @Query("SELECT * FROM categoria")
    suspend fun listar(): List<Categoria>

    @Transaction
    @Query("SELECT * FROM categoria")
    suspend fun getCategoriaComTransacao(): CategoriaComTransacao
}