package br.com.devmobile.contacerta.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.devmobile.contacerta.R
import br.com.devmobile.contacerta.data.entity.Categoria
import br.com.devmobile.contacerta.data.repository.RepositoryCategoriaImpl
import br.com.devmobile.contacerta.databinding.ActivityAddCategoriaBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddCategoriaActivity : AppCompatActivity() {

    private val binding by lazy{ ActivityAddCategoriaBinding.inflate(layoutInflater) }
    private val repositoryCategoriaImpl by lazy { RepositoryCategoriaImpl(this) }
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


        binding.btSalvarcategoria.setOnClickListener {
            val categotia = binding.editNomeCategoria.text.toString()
            if(categotia.isNotEmpty()){
                val categotia = Categoria(0, categotia)
                coroutine.launch {
                    val id = repositoryCategoriaImpl.salvar( categotia )

                    withContext(Dispatchers.Main){
                        if(id > 0){
                            Toast.makeText(applicationContext, "Categoria salva com sucesso", Toast.LENGTH_SHORT).show()
                            direcionarTelaInicial()
                        }else{
                            Toast.makeText(applicationContext, "Erro ao tentar categoria", Toast.LENGTH_SHORT).show()
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

    private fun direcionarTelaInicial(){
          startActivity(Intent(this, MainActivity::class.java))
          overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}