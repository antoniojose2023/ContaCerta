package br.com.devmobile.contacerta.data.repository

import android.content.Context
import br.com.devmobile.contacerta.data.database.Database
import br.com.devmobile.contacerta.data.entity.Transacao
import br.com.devmobile.contacerta.data.entity.relation.TransacaoComCategoria

class RepositoryTransacaoImpl(val context: Context) {

    val dao = Database.getInstance(context).transacaoDao()

    suspend fun salvar(transacao: Transacao): Long {
        return  try {
            dao.salvar(transacao)
        }catch (ex: Exception){
            throw Exception(ex.message)
        }
    }

    suspend fun delete(transacao: Transacao): Int {
        return  try {
            dao.delete(transacao)
        }catch (ex: Exception){
            throw Exception(ex.message)
        }
    }

    suspend fun atualizar(transacao: Transacao): Int {
        return  try {
            dao.atualizar(transacao)
        }catch (ex: Exception){
            throw Exception(ex.message)
        }
    }

    suspend fun listar(): List<Transacao> {
        return  try {
            dao.listar()
        }catch (ex: Exception){
            throw Exception(ex.message)
        }
    }

    suspend fun listarTransacaoComCategoria(): List<TransacaoComCategoria> {
        return  try {
            dao.listarTransacaoComCategoria()
        }catch (ex: Exception){
            throw Exception(ex.message)
        }
    }

    suspend fun somaReceitaOuDespesa(tipo: String): Double {
          return try{
              dao.sumValorReceitaOuDespesa(tipo)
          }catch (ex: Exception){
              throw Exception(ex.message)
          }
    }

    suspend fun getReceitaOuDespesa(tipo: String): List<TransacaoComCategoria> {
        return try{
            dao.getReceitaOuDespesa(tipo)
        }catch (ex: Exception){
            throw Exception(ex.message)
        }
    }

    suspend fun listarTransacaoPorNome(nomeTransacao: String): List<TransacaoComCategoria> {
        return try{
            dao.listarTransacaoPorNome(nomeTransacao)
        }catch (ex: Exception){
            throw Exception(ex.message)
        }
    }
}