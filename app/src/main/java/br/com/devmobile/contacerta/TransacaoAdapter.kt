package br.com.devmobile.contacerta

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.devmobile.contacerta.data.entity.Categoria
import br.com.devmobile.contacerta.data.entity.Transacao
import br.com.devmobile.contacerta.data.entity.relation.CategoriaComTransacao
import br.com.devmobile.contacerta.data.entity.relation.TransacaoComCategoria
import br.com.devmobile.contacerta.databinding.ItemTransacoesBinding
import br.com.devmobile.contacerta.util.toReal

class TransacaoAdapter(
    var onClickListener: (TransacaoComCategoria) -> Unit = {},
    var onClickLongListener: (TransacaoComCategoria) -> Unit = {}
): RecyclerView.Adapter<TransacaoAdapter.TransacaoViewHolder>()  {

    private var transacaoComCategoria = mutableListOf<TransacaoComCategoria>()
    fun addLista(lista: List<TransacaoComCategoria>){
        transacaoComCategoria = lista as MutableList<TransacaoComCategoria>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransacaoViewHolder {
           val layoutInflater = LayoutInflater.from(parent.context)
           val binding = ItemTransacoesBinding.inflate(layoutInflater, parent, false)
           return TransacaoViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TransacaoViewHolder,
        position: Int
    ) {
          val item = transacaoComCategoria[position]
          holder.bind(item)
    }

    override fun getItemCount() = transacaoComCategoria.size

    inner class TransacaoViewHolder(val binding: ItemTransacoesBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(transacaoComCategoria: TransacaoComCategoria){

                binding.tvItemTituloTransacaco.text = transacaoComCategoria.transacao.nome
                binding.tvItemValorTransacao.text = transacaoComCategoria.transacao.valor.toReal()
                binding.tvItemCategoriaTransacao.text = transacaoComCategoria.categoria.nome
                binding.tvItemDataTransacao.text = transacaoComCategoria.transacao.data

                binding.tvItemValorTransacao.setTextColor(
                    if(transacaoComCategoria.transacao.tipo == "DESPESA") ContextCompat.getColor(itemView.context, R.color.vermelho)
                    else ContextCompat.getColor(itemView.context, R.color.verde))

                binding.itemCardTransacao.setOnClickListener {
                      onClickListener(transacaoComCategoria)
                }

                binding.itemCardTransacao.setOnLongClickListener {
                    onClickLongListener(transacaoComCategoria)
                    true
                }
        }
    }

}