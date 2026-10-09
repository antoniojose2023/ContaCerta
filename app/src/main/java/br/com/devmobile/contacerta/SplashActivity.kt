package br.com.devmobile.contacerta

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.devmobile.contacerta.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private val binding by lazy{ ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

         carregaTelaInicial()

    }

    fun carregaTelaInicial(){

        Handler(Looper.getMainLooper()).postDelayed({
              startActivity(Intent(this, MainActivity::class.java))
              overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }, 2000)

    }

}