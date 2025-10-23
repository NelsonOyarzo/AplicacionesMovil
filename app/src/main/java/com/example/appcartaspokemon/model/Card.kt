package com.example.appcartaspokemon.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Modelo de datos para representar una carta de Pokémon.
 * Implementa Parcelable para poder pasar objetos entre Activities.
 */
@Parcelize
data class Card(
    val id: String,
    val name: String,
    val type: String,
    val quality: String,
    val price: Double,
    val imageUrl: String,
    val description: String = ""
) : Parcelable {

    /**
     * Formatea el precio con el símbolo de dólar y dos decimales.
     */
    fun getFormattedPrice(): String {
        return "$%.2f".format(price)
    }

    /**
     * Verifica si la carta coincide con un término de búsqueda.
     */
    fun matchesSearch(query: String): Boolean {
        val lowerQuery = query.lowercase()
        return name.lowercase().contains(lowerQuery) ||
               type.lowercase().contains(lowerQuery) ||
               quality.lowercase().contains(lowerQuery)
    }
}
