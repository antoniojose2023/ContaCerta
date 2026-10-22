package br.com.devmobile.contacerta.data.repository

import android.content.Context
import br.com.devmobile.contacerta.data.database.Database
import br.com.devmobile.contacerta.data.entity.Conta

class RepositoryContaImpl(val context: Context) {

    val dao = Database.getInstance(context).contaDao()

    suspend fun salvar(conta: Conta): Long {
         return  try {
              dao.salvar(conta)
         }catch (ex: Exception){
             throw Exception(ex.message)
         }
    }

   suspend fun delete(conta: Conta): Int {
        return  try {
            dao.delete(conta)
        }catch (ex: Exception){
            throw Exception(ex.message)
        }
    }

    suspend fun atualizar(conta: Conta): Int {
        return  try {
            dao.atualizar(conta)
        }catch (ex: Exception){
            throw Exception(ex.message)
        }
    }

   suspend fun listar(): List<Conta> {
        return  try {
            dao.listar()
        }catch (ex: Exception){
            throw Exception(ex.message)
        }
    }
}