package br.com.devmobile.contacerta.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.devmobile.contacerta.R
import br.com.devmobile.contacerta.data.entity.Transacao
import br.com.devmobile.contacerta.data.entity.relation.TransacaoComCategoria
import br.com.devmobile.contacerta.data.repository.RepositoryTransacaoImpl
import br.com.devmobile.contacerta.databinding.ActivityAtualizaTransacaoBinding
import br.com.devmobile.contacerta.fragment.TransacoesFragment
import br.com.devmobile.contacerta.util.toReal
import br.com.devmobile.contacerta.util.toValorDouble
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AtualizaTransacaoActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityAtualizaTransacaoBinding.inflate( layoutInflater ) }

    private val repositoryTransacaoImpl by lazy{ RepositoryTransacaoImpl(this) }
    private lateinit var transacaoComCategoria: TransacaoComCategoria
    private val coroutine = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle = intent.extras
        if(bundle != null){

             transacaoComCategoria = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                 bundle.getSerializable("transacaoComCategoria", TransacaoComCategoria::class.java) as TransacaoComCategoria
             } else {
                 bundle.getSerializable("transacaoComCategoria") as TransacaoComCategoria
             }

             populaCampos(transacaoComCategoria)
        }


        binding.btAtualizarTransacao.setOnClickListener {
              val nome = binding.editNomeTransacaoAtualizar.text.toString()
              val valor = binding.editValorAtualizar.text.toString()

              val v = valor.toValorDouble()

              if(nome.isNotEmpty() && valor.isNotEmpty()){
                   val transacao = transacaoComCategoria.transacao.copy(nome = nome, valor = v)
                   coroutine.launch {
                        val qtd = repositoryTransacaoImpl.atualizar(transacao)
                        withContext(Dispatchers.Main){
                             if(qtd > 0){
                                  Toast.makeText(applicationContext, "Transação atualizada com sucesso", Toast.LENGTH_SHORT).show()
                                  atualizaTelaTransacao()
                             }else{
                                  Toast.makeText(applicationContext, "Erro ao atualizar transação", Toast.LENGTH_SHORT).show()
                             }
                        }
                   }
              }else{
                  Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
              }
        }
    }

    fun populaCampos(transacaoComCategoria: TransacaoComCategoria){

           if(transacaoComCategoria != null){
                binding.editNomeTransacaoAtualizar.setText(transacaoComCategoria.transacao.nome)
                binding.editValorAtualizar.setText(transacaoComCategoria.transacao.valor.toReal())
           }
    }

    fun atualizaTelaTransacao(){
         startActivity(Intent(this, MainActivity::class.java))
         overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}