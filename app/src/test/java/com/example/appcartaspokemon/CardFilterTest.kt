package com.example.appcartaspokemon

import com.example.appcartaspokemon.model.Card
import org.junit.Test
import org.junit.Assert.*

class CardFilterTest {

    @Test
    fun testCardMatchesSearchByName() {
        val card = Card(
            id = "1",
            name = "Charizard",
            type = "Fuego",
            quality = "Mint",
            price = 450.0,
            imageUrl = ""
        )

        assertTrue(card.matchesSearch("char"))
        assertTrue(card.matchesSearch("CHARIZARD"))
        assertFalse(card.matchesSearch("pikachu"))
    }

    @Test
    fun testCardMatchesSearchByType() {
        val card = Card(
            id = "2",
            name = "Blastoise",
            type = "Agua",
            quality = "Mint",
            price = 280.0,
            imageUrl = ""
        )

        assertTrue(card.matchesSearch("agua"))
        assertTrue(card.matchesSearch("AGUA"))
    }

    @Test
    fun testCardMatchesSearchByQuality() {
        val card = Card(
            id = "3",
            name = "Venusaur",
            type = "Planta",
            quality = "Near Mint",
            price = 195.0,
            imageUrl = ""
        )

        assertTrue(card.matchesSearch("near"))
        assertTrue(card.matchesSearch("mint"))
    }

    @Test
    fun testFormattedPrice() {
        val card = Card(
            id = "4",
            name = "Pikachu",
            type = "Eléctrico",
            quality = "Mint",
            price = 125.5,
            imageUrl = ""
        )

        assertEquals("$125.50", card.getFormattedPrice())
    }
}
