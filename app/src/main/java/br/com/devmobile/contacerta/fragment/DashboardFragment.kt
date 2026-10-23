package br.com.devmobile.contacerta.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.devmobile.contacerta.R
import br.com.devmobile.contacerta.TransacaoAdapter
import br.com.devmobile.contacerta.data.entity.relation.TransacaoComCategoria
import br.com.devmobile.contacerta.data.repository.RepositoryCategoriaImpl
import br.com.devmobile.contacerta.data.repository.RepositoryTransacaoImpl
import br.com.devmobile.contacerta.databinding.FragmentDashboardBinding
import br.com.devmobile.contacerta.util.toReal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DashboardFragment : Fragment() {

    //private val repositoryCategoriaImpl by lazy{ RepositoryCategoriaImpl(requireContext()) }
    private val repositoryTransacaoImpl by lazy{ RepositoryTransacaoImpl(requireContext()) }
    private val coroutine = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private var receita = 0.0
    private var despesa = 0.0

    private val transacaoAdapter by lazy { TransacaoAdapter() }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDashboardBinding.inflate(inflater, container, false)

        binding.rvTransacoes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTransacoes.adapter = transacaoAdapter

        binding.tvVerTodos.setOnClickListener {
            val fragment = TransacoesFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainerView, fragment)
                ?.commit()
        }

        coroutine.launch {
            val transacaoesComTransacao = repositoryTransacaoImpl.listarTransacaoComCategoria()

            withContext(Dispatchers.Main){
                if(transacaoesComTransacao.isNotEmpty()){
                        transacaoAdapter.addLista( transacaoesComTransacao.take(4) )
                }else{
                      Toast.makeText(requireContext(), "Lista vázia", Toast.LENGTH_SHORT).show()
                }
            }
        }

        coroutine.launch {
              receita = repositoryTransacaoImpl.somaReceitaOuDespesa("RECEITA")
              despesa = repositoryTransacaoImpl.somaReceitaOuDespesa("DESPESA")

              withContext(Dispatchers.Main){
                   binding.tvReceitaAtual.text = receita.toReal()
                   binding.tvDespesasAtual.text = despesa.toReal()
                   binding.tvSaldo.text = (receita - despesa).toReal()
              }
        }

        transacaoAdapter.onClickLongListener = { transacaoComCategoria ->
               exibirDialog(transacaoComCategoria)
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
                             atualizarTelaDashBoard()
                         }else{
                             Toast.makeText(requireContext(), "Erro ao excluir transação", Toast.LENGTH_SHORT).show()
                         }
                     }
                }
         }
         dialog.setNeutralButton("Cancelar"){_,_->

         }
         .create()
         .show()

    }


    fun atualizarTelaDashBoard(){
        val fragment = DashboardFragment()
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentContainerView, fragment)
            ?.commit()
    }

}