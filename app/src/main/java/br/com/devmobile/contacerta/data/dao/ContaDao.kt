package br.com.devmobile.contacerta.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.devmobile.contacerta.data.entity.Conta

@Dao
interface ContaDao {

    @Insert
    suspend fun salvar(conta: Conta): Long

    @Update
    suspend fun atualizar(conta: Conta): Int

    @Delete
    suspend fun delete(conta: Conta): Int

    @Query("SELECT * FROM conta")
    suspend fun listar(): List<Conta>

}