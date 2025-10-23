package com.example.appcartaspokemon.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interfaz de servicio para obtener precios de mercado de cartas.
 * Esta es una interfaz de ejemplo que muestra cómo integrar una API externa.
 * 
 * NOTA: Para usar en producción, necesitas:
 * 1. Implementar Retrofit con la URL base de la API real
 * 2. Agregar tu API key en los headers o como query parameter
 * 3. Manejar la autenticación según los requisitos de la API
 */
interface PriceService {

    /**
     * Ejemplo de endpoint para obtener el precio de una carta.
     * @param cardName Nombre de la carta
     * @param apiKey Tu clave de API (si es necesaria)
     */
    @GET("v1/prices")
    fun getCardPrice(
        @Query("name") cardName: String,
        @Query("api_key") apiKey: String
    ): Call<PriceResponse>
}

/**
 * Modelo de respuesta de la API de precios.
 */
data class PriceResponse(
    val cardName: String,
    val marketPrice: Double,
    val lowPrice: Double,
    val highPrice: Double,
    val currency: String
)
