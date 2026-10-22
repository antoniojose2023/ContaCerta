package br.com.devmobile.contacerta.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.devmobile.contacerta.R
import br.com.devmobile.contacerta.data.entity.Categoria
import br.com.devmobile.contacerta.data.entity.Transacao
import br.com.devmobile.contacerta.data.repository.RepositoryCategoriaImpl
import br.com.devmobile.contacerta.data.repository.RepositoryTransacaoImpl
import br.com.devmobile.contacerta.databinding.ActivityNovaTransacaoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.getValue

class NovaTransacaoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityNovaTransacaoBinding.inflate(layoutInflater) }
    private var isClickedReceita = false
    private var isClickedDespesas = false
    private var textoTipoTransacao =""

    private val repositoryCategoriaImpl by lazy{ RepositoryCategoriaImpl(this) }
    private val repositoryTransacaoImpl by lazy{ RepositoryTransacaoImpl(this) }
    private val coroutine = CoroutineScope(Dispatchers.IO)

    private lateinit var categoriaModel: Categoria

    private val contasSpinner = listOf("001")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.imageButtonConta.setOnClickListener {
            startActivity(Intent(this, AddContaActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_in)
        }

        binding.imageButtonCategoria.setOnClickListener {
            startActivity(Intent(this, AddCategoriaActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_in)
        }

        binding.btDespesa.setOnClickListener {
              isClickedDespesas =  !isClickedDespesas
              if (isClickedDespesas){
                  binding.btDespesa.backgroundTintList = getColorStateList(R.color.verde)
                  binding.btDespesa.setTextColor(ContextCompat.getColor(this, R.color.white))
                  textoTipoTransacao= "DESPESA"
              }else{
                  binding.btDespesa.backgroundTintList = getColorStateList(R.color.verde_10)
                  binding.btDespesa.setTextColor(ContextCompat.getColor(this, R.color.black))
                  textoTipoTransacao = ""
              }
        }

        binding.btReceitaNovaTransacao.setOnClickListener {
            isClickedReceita =  !isClickedReceita
            if (isClickedReceita){
                binding.btReceitaNovaTransacao.backgroundTintList = getColorStateList(R.color.verde)
                binding.btReceitaNovaTransacao.setTextColor(ContextCompat.getColor(this, R.color.white))
                textoTipoTransacao = "RECEITA"
            }else{
                binding.btReceitaNovaTransacao.backgroundTintList = getColorStateList(R.color.verde_10)
                binding.btReceitaNovaTransacao.setTextColor(ContextCompat.getColor(this, R.color.black))
                textoTipoTransacao = ""
            }
        }

        coroutine.launch {
            val listacategoriaSpinner = repositoryCategoriaImpl.listar()
            val nomeCategorias = listacategoriaSpinner.map { item->
                  item.nome
            }

            val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, nomeCategorias)
            withContext(Dispatchers.Main){
                binding.spinnerCategoria.adapter = adapter

                binding.spinnerCategoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected( p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long ) {
                        if(p2 in listacategoriaSpinner.indices)
                            categoriaModel = listacategoriaSpinner[p2]
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }
                }

            }


        }


        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, contasSpinner)
        binding.spinnerConta.adapter = adapter

        binding.btSalvarTransacao.setOnClickListener {

             val tipo = textoTipoTransacao
             val transacaco = binding.editNomeTransacao.text.toString()
             val valor = binding.editValor.text.toString()
             val data = binding.editData.text.toString()
             val observacao = binding.editdescricaoTransacao.text.toString()
             val conta = binding.spinnerConta.selectedItem.toString()
             val categoria = binding.spinnerCategoria.selectedItem.toString()

             if(tipo.isNotEmpty() && transacaco.isNotEmpty() && valor.isNotEmpty() && data.isNotEmpty() && observacao.isNotEmpty() && conta.isNotEmpty() && categoria.isNotEmpty()){
                    coroutine.launch {
                          val transacao = Transacao(0, transacaco, observacao, valor.toDouble(), tipo, data, categoriaModel.id, conta)
                          val id = repositoryTransacaoImpl.salvar(transacao)

                           withContext(Dispatchers.Main){
                                 if(id > 0){
                                     Toast.makeText(applicationContext, "Transação salva com sucesso", Toast.LENGTH_SHORT).show()
                                     direcionarTelaInicial()
                                 }else{
                                     Toast.makeText(applicationContext, "Erro ao tentar salvar a transação", Toast.LENGTH_SHORT).show()
                                 }
                           }

                    }
             }else{
                 Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
             }

        }

    }

    private fun direcionarTelaInicial(){
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}