package br.com.devmobile.contacerta.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.devmobile.contacerta.R
import br.com.devmobile.contacerta.data.entity.Conta
import br.com.devmobile.contacerta.data.repository.RepositoryContaImpl
import br.com.devmobile.contacerta.databinding.ActivityAddContaBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddContaActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityAddContaBinding.inflate( layoutInflater ) }

    private val repositoryContaImpl by lazy { RepositoryContaImpl(this) }
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


        binding.btsalvarConta.setOnClickListener {
            val conta = binding.editNomeConta.text.toString()
            if(conta.isNotEmpty()){
                val conta = Conta(0, conta)
                coroutine.launch {
                      val id = repositoryContaImpl.salvar( conta )

                      withContext(Dispatchers.Main){
                           if(id > 0){
                               Toast.makeText(applicationContext, "Conta salva com sucesso", Toast.LENGTH_SHORT).show()
                               finish()
                           }else{
                               Toast.makeText(applicationContext, "Erro ao tentar", Toast.LENGTH_SHORT).show()
                           }
                      }
                }

            }else{
                Toast.makeText(applicationContext, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onStop() {
        super.onStop()
        coroutine.cancel()
    }
}