package br.com.devmobile.contacerta.data.database

import android.content.Context

import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.devmobile.contacerta.data.dao.CategoriaDao
import br.com.devmobile.contacerta.data.dao.ContaDao
import br.com.devmobile.contacerta.data.dao.TransacaoDao
import br.com.devmobile.contacerta.data.entity.Categoria
import br.com.devmobile.contacerta.data.entity.Conta
import br.com.devmobile.contacerta.data.entity.Transacao


@androidx.room.Database(entities = [Conta::class, Categoria::class, Transacao::class], version = 1)
abstract class Database: RoomDatabase() {

    abstract fun contaDao(): ContaDao
    abstract fun categoriaDao(): CategoriaDao
    abstract fun transacaoDao(): TransacaoDao

    companion object{
        fun getInstance(context: Context): Database{
            return Room.databaseBuilder(
                   context,
                  Database::class.java,
                  "db_contacerta"
            ).build()
        }

    }

}