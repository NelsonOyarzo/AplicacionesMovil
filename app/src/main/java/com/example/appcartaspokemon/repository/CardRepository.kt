package com.example.appcartaspokemon.repository

import android.content.Context
import com.example.appcartaspokemon.model.Card
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

/**
 * Repositorio para gestionar las operaciones de datos de las cartas.
 * Carga datos desde assets y mantiene una lista en memoria.
 */
class CardRepository(private val context: Context) {

    private val cardList = mutableListOf<Card>()
    private var nextId = 1000 // ID para nuevas cartas

    init {
        loadCardsFromAssets()
    }

    /**
     * Carga las cartas desde el archivo JSON en assets.
     */
    private fun loadCardsFromAssets() {
        try {
            val jsonString = context.assets.open("cards.json")
                .bufferedReader()
                .use { it.readText() }

            val listType = object : TypeToken<List<Card>>() {}.type
            val cards: List<Card> = Gson().fromJson(jsonString, listType)
            cardList.addAll(cards)

            // Actualizar nextId basado en los IDs existentes
            val maxId = cards.mapNotNull { it.id.toIntOrNull() }.maxOrNull() ?: 0
            nextId = maxId + 1
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * Obtiene todas las cartas.
     */
    fun getAllCards(): List<Card> {
        return cardList.toList()
    }

    /**
     * Agrega una nueva carta a la colección.
     */
    fun addCard(name: String, type: String, quality: String, price: Double, imageUrl: String): Card {
        val newCard = Card(
            id = nextId.toString(),
            name = name,
            type = type,
            quality = quality,
            price = price,
            imageUrl = imageUrl.ifEmpty { "https://via.placeholder.com/200x280?text=No+Image" }
        )
        cardList.add(newCard)
        nextId++
        return newCard
    }

    /**
     * Busca una carta por ID.
     */
    fun getCardById(id: String): Card? {
        return cardList.find { it.id == id }
    }
}
