package br.com.devmobile.contacerta.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import br.com.devmobile.contacerta.R
import br.com.devmobile.contacerta.databinding.ActivityMainBinding
import br.com.devmobile.contacerta.fragment.DashboardFragment
import br.com.devmobile.contacerta.fragment.RelatorioFragment
import br.com.devmobile.contacerta.fragment.TransacoesFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityMainBinding.inflate( layoutInflater ) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        addFragment(DashboardFragment())


        binding.bottomAppBar.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.menu_dashboard -> {
                    addFragment(DashboardFragment())
                }
                R.id.menu_transacoes -> {
                    addFragment(TransacoesFragment())
                }
                R.id.menu_relatorio -> {
                     addFragment(RelatorioFragment())
                }
                else -> {
                    Log.i("error", "onCreate: "+"Não carregou nenhum fragment")}
            }

               true
        }

    }


    fun addFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

}