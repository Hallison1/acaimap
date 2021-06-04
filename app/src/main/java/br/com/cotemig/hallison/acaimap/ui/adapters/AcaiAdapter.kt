package br.com.cotemig.hallison.acaimap.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.hallison.acaimap.R
import br.com.cotemig.hallison.acaimap.model.Loja
import com.bumptech.glide.Glide

class AcaiAdapter (var list: List<Loja>) :
        RecyclerView.Adapter<AcaiAdapter.LojaHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LojaHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LojaHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: LojaHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class LojaHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_vitrine, parent, false)) {
        private var nameView: TextView? = null
        private var enderecoView: TextView? = null
        private var pictureView: ImageView? = null
        private var recyclerView: RecyclerView? = null

        init {
            nameView = itemView.findViewById(R.id.name)
            enderecoView = itemView.findViewById(R.id.endereco)
            pictureView = itemView.findViewById(R.id.picture)
            recyclerView = itemView.findViewById(R.id.acaiList)
        }

        fun bind(loja: Loja) {
            nameView?.text = loja.name
            enderecoView?.text = loja.endereco
            Glide
                .with(itemView)
                .load(loja.picture)
                .centerCrop()
                .placeholder(R.drawable.user)
                .into(pictureView!!);
        }

    }
}