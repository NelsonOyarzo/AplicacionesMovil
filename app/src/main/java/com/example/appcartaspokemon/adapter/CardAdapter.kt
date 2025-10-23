package com.example.appcartaspokemon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.appcartaspokemon.R
import com.example.appcartaspokemon.model.Card

/**
 * Adaptador para mostrar la lista de cartas en un RecyclerView.
 * Implementa Filterable para permitir búsquedas en tiempo real.
 */
class CardAdapter(
    private var cardList: List<Card>,
    private val onCardClick: (Card) -> Unit
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>(), Filterable {

    // Lista completa sin filtrar
    private var cardListFull = cardList.toList()

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.ivCardImage)
        val nameTextView: TextView = itemView.findViewById(R.id.tvCardName)
        val typeTextView: TextView = itemView.findViewById(R.id.tvCardType)
        val qualityTextView: TextView = itemView.findViewById(R.id.tvCardQuality)
        val priceTextView: TextView = itemView.findViewById(R.id.tvCardPrice)

        fun bind(card: Card) {
            nameTextView.text = card.name
            typeTextView.text = "Tipo: ${card.type}"
            qualityTextView.text = "Calidad: ${card.quality}"
            priceTextView.text = card.getFormattedPrice()

            // Cargar imagen con Glide
            Glide.with(itemView.context)
                .load(card.imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)

            // Click listener
            itemView.setOnClickListener {
                onCardClick(card)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(cardList[position])
    }

    override fun getItemCount(): Int = cardList.size

    /**
     * Actualiza la lista completa de cartas.
     */
    fun updateCards(newCards: List<Card>) {
        cardList = newCards
        cardListFull = newCards.toList()
        notifyDataSetChanged()
    }

    /**
     * Implementación del filtro para búsqueda.
     */
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = if (constraint.isNullOrEmpty()) {
                    cardListFull
                } else {
                    cardListFull.filter { card ->
                        card.matchesSearch(constraint.toString())
                    }
                }

                return FilterResults().apply {
                    values = filteredList
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                cardList = results?.values as? List<Card> ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }
}
