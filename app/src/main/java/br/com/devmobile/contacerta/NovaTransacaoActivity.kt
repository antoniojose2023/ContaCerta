package br.com.devmobile.contacerta

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.devmobile.contacerta.databinding.ActivityNovaTransacaoBinding

class NovaTransacaoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityNovaTransacaoBinding.inflate(layoutInflater) }

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

    }
}