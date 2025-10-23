package com.example.appcartaspokemon.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appcartaspokemon.model.Card
import com.example.appcartaspokemon.repository.CardRepository

/**
 * ViewModel para gestionar los datos de las cartas.
 * Mantiene la separación entre la UI y la lógica de datos.
 */
class CardViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CardRepository(application)
    private val _cards = MutableLiveData<List<Card>>()
    val cards: LiveData<List<Card>> = _cards

    init {
        loadCards()
    }

    /**
     * Carga todas las cartas desde el repositorio.
     */
    fun loadCards() {
        _cards.value = repository.getAllCards()
    }

    /**
     * Agrega una nueva carta.
     */
    fun addCard(name: String, type: String, quality: String, price: Double, imageUrl: String): Card {
        val newCard = repository.addCard(name, type, quality, price, imageUrl)
        loadCards() // Recargar la lista
        return newCard
    }

    /**
     * Busca una carta por ID.
     */
    fun getCardById(id: String): Card? {
        return repository.getCardById(id)
    }
}
