package br.com.devmobile.contacerta.data.repository

import android.content.Context
import br.com.devmobile.contacerta.data.database.Database
import br.com.devmobile.contacerta.data.entity.Categoria
import br.com.devmobile.contacerta.data.entity.Transacao
import br.com.devmobile.contacerta.data.entity.relation.CategoriaComTransacao

class RepositoryCategoriaImpl(val context: Context) {

    val dao = Database.getInstance(context).categoriaDao()

    suspend fun salvar(categoria: Categoria): Long {
        return  try {
            dao.salvar(categoria)
        }catch (ex: Exception){
            throw Exception(ex.message)
        }
    }

    suspend fun delete(categoria: Categoria): Int {
        return  try {
            dao.delete(categoria)
        }catch (ex: Exception){
            throw Exception(ex.message)
        }
    }

    suspend fun atualizar(categoria: Categoria): Int {
        return  try {
            dao.atualizar(categoria)
        }catch (ex: Exception){
            throw Exception(ex.message)
        }
    }

    suspend fun listar(): List<Categoria> {
        return  try {
            dao.listar()
        }catch (ex: Exception){
            throw Exception(ex.message)
        }
    }

    suspend fun getCategoriaComTransacao(): CategoriaComTransacao{
        return  try {
            dao.getCategoriaComTransacao()
        }catch (ex: Exception){
            throw Exception(ex.message)
        }
    }
}