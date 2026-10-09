package br.com.devmobile.contacerta.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.devmobile.contacerta.NovaTransacaoActivity
import br.com.devmobile.contacerta.R
import br.com.devmobile.contacerta.databinding.FragmentTransacoesBinding


class TransacoesFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val binding =  inflater.inflate(R.layout.fragment_transacoes, container, false)
        val binding = FragmentTransacoesBinding.inflate(inflater, container, false)

        binding.floatingActionButtonAddicionarTransacao.setOnClickListener {
                 startActivity(Intent(container?.context, NovaTransacaoActivity::class.java))
        }

        return binding.root
    }


}