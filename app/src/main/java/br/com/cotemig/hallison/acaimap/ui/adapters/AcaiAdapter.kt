package br.com.cotemig.hallison.acaimap.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.hallison.acaimap.R
import br.com.cotemig.hallison.acaimap.model.Acai
import com.bumptech.glide.Glide

class AcaiAdapter (var list: List<Acai>) :
        RecyclerView.Adapter<AcaiAdapter.AcaiHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcaiHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AcaiHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: AcaiHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class AcaiHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_acai, parent, false)) {
        private var nameView: TextView? = null
        private var pictureView: ImageView? = null

        init {
            nameView = itemView.findViewById(R.id.tipoAcai)
            pictureView = itemView.findViewById(R.id.acaiImage)
        }

        fun bind(acai: Acai) {
            nameView?.text = acai.tipo
            Glide
                .with(itemView)
                .load(acai.imagem)
                .centerCrop()
                .placeholder(R.drawable.user)
                .into(pictureView!!);
        }

    }
}