package br.com.devmobile.contacerta.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.devmobile.contacerta.R
import br.com.devmobile.contacerta.TransacaoAdapter
import br.com.devmobile.contacerta.data.entity.relation.TransacaoComCategoria
import br.com.devmobile.contacerta.data.repository.RepositoryTransacaoImpl
import br.com.devmobile.contacerta.ui.NovaTransacaoActivity
import br.com.devmobile.contacerta.databinding.FragmentTransacoesBinding
import br.com.devmobile.contacerta.ui.AtualizaTransacaoActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TransacoesFragment : Fragment() {


    private val repositoryTransacaoImpl by lazy{ RepositoryTransacaoImpl(requireContext()) }
    private val transacaoAdapter by lazy { TransacaoAdapter() }
    private val coroutine = CoroutineScope(Dispatchers.IO)

    private var isClickedReceita = false
    private var isClickedDespesas = false
    private var isClickedTodas = false

    private var todasTransacoes : MutableList<TransacaoComCategoria> = mutableListOf()

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

        binding.rvTransacoesListagem.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTransacoesListagem.adapter = transacaoAdapter

        binding.floatingActionButtonAddicionarTransacao.setOnClickListener {
                 startActivity(Intent(container?.context, NovaTransacaoActivity::class.java))
        }

        coroutine.launch {
            val transacaoesComTransacao = repositoryTransacaoImpl.listarTransacaoComCategoria()

            withContext(Dispatchers.Main){
                if(transacaoesComTransacao.isNotEmpty()){
                    todasTransacoes = transacaoesComTransacao.toMutableList()
                    transacaoAdapter.addLista( transacaoesComTransacao)
                }else{
                    Toast.makeText(requireContext(), "Lista vázia", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //Traz as receitas
        binding.btReceitas.setOnClickListener {
            isClickedReceita =  !isClickedReceita
            if (isClickedReceita){
                binding.btReceitas.backgroundTintList = getColorStateList(requireContext(),R.color.verde)
                binding.btReceitas.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                coroutine.launch {
                    val transacoesTipoReceita = repositoryTransacaoImpl.getReceitaOuDespesa("RECEITA")
                    withContext(Dispatchers.Main){
                        transacaoAdapter.addLista( transacoesTipoReceita )
                    }
                }
            }else{
                binding.btReceitas.backgroundTintList = getColorStateList(requireContext(), R.color.verde_10)
                binding.btReceitas.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

            }
        }

        //Traz as despesas
        binding.btDespesas.setOnClickListener {
            isClickedDespesas =  !isClickedDespesas
            if (isClickedDespesas){
                binding.btDespesas.backgroundTintList = getColorStateList(requireContext(),R.color.verde)
                binding.btDespesas.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                coroutine.launch {
                    val transacoesTipoReceita = repositoryTransacaoImpl.getReceitaOuDespesa("DESPESA")
                    withContext(Dispatchers.Main){
                        transacaoAdapter.addLista( transacoesTipoReceita )
                    }
                }
            }else{
                binding.btDespesas.backgroundTintList = getColorStateList(requireContext(), R.color.verde_10)
                binding.btDespesas.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            }
        }

        binding.btTodasTransacoes.setOnClickListener {
            isClickedTodas =  !isClickedTodas
            if (isClickedTodas){
                binding.btTodasTransacoes.backgroundTintList = getColorStateList(requireContext(),R.color.verde)
                binding.btTodasTransacoes.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                if(todasTransacoes.isNotEmpty()){
                      transacaoAdapter.addLista( todasTransacoes )
                }
            }else{
                binding.btTodasTransacoes.backgroundTintList = getColorStateList(requireContext(), R.color.verde_10)
                binding.btTodasTransacoes.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            }
        }


        binding.editPesquisaTransacoes.setOnEditorActionListener(TextView.OnEditorActionListener { edit, p1, p2 ->
            val textoDigitado = edit?.text.toString()
            if(textoDigitado.isNotEmpty()){
                  coroutine.launch {
                        val transacoesComTransacao = repositoryTransacaoImpl.listarTransacaoPorNome(textoDigitado)
                        withContext(Dispatchers.Main){

                              if(transacoesComTransacao.isNotEmpty()){
                                     transacaoAdapter.addLista( transacoesComTransacao )
                              }else{
                                  Toast.makeText(requireContext(), "Lista vázia", Toast.LENGTH_SHORT).show()
                              }
                        }
                  }

            }
                true
         })

        transacaoAdapter.onClickLongListener = { transacaoComCategoria ->
            exibirDialog(transacaoComCategoria)
        }

        transacaoAdapter.onClickListener = { transacaoComCategoria ->
            val intent = Intent(requireContext(), AtualizaTransacaoActivity::class.java)
            intent.putExtra("transacaoComCategoria", transacaoComCategoria)
            activity?.startActivity( intent )
        }

        return binding.root
    }

    fun exibirDialog(transacaoComCategoria: TransacaoComCategoria){
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle("Deseja realmente excluir?")
        dialog.setMessage("Essa ação não pode ser desfeita")
        dialog.setPositiveButton("Sim"){_,_ ->
            coroutine.launch {
                val qtd = repositoryTransacaoImpl.delete(transacaoComCategoria)
                withContext(Dispatchers.Main){
                    if(qtd > 0){
                        Toast.makeText(requireContext(), "Transação excluída com sucesso", Toast.LENGTH_SHORT).show()
                        atualizarTelaListagemTransacoes()
                    }else{
                        Toast.makeText(requireContext(), "Erro ao excluir transação", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        dialog.setNeutralButton("Cancelar"){_,_-> }
            .create()
            .show()

    }


    fun atualizarTelaListagemTransacoes(){
        val fragment = DashboardFragment()
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentContainerView, fragment)
            ?.commit()
    }


}