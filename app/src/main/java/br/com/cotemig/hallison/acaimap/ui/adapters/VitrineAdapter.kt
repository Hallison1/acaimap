package br.com.cotemig.hallison.acaimap.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.hallison.acaimap.R
import br.com.cotemig.hallison.acaimap.model.Loja
import com.bumptech.glide.Glide


class VitrineAdapter (var list: List<Loja>, var context: Context) :
        RecyclerView.Adapter<VitrineAdapter.LojaHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LojaHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LojaHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: LojaHolder, position: Int) {
        holder.bind(list[position], context)
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

        fun bind(loja: Loja, context: Context) {

            recyclerView!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView!!.adapter = AcaiAdapter(loja.acais)

            val dividerItemDecoration = DividerItemDecoration(
                recyclerView!!.context, DividerItemDecoration.HORIZONTAL
            )
            recyclerView!!.addItemDecoration(dividerItemDecoration)
            ContextCompat.getDrawable(context, R.drawable.divider)?.let {
                dividerItemDecoration.setDrawable(
                    it
                )
            };

            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(recyclerView)

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