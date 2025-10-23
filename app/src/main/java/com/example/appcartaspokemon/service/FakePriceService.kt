package com.example.appcartaspokemon.service

import kotlin.random.Random

/**
 * Implementación falsa del servicio de precios para pruebas y desarrollo.
 * Simula respuestas de una API real sin hacer llamadas de red.
 * 
 * USO: Esta clase se usa actualmente en la aplicación para demostrar
 * cómo funcionaría la integración con una API real de precios.
 * 
 * REEMPLAZO: Para usar una API real:
 * 1. Configura Retrofit en tu Activity o ViewModel:
 *    val retrofit = Retrofit.Builder()
 *        .baseUrl("https://api.tuservicio.com/")
 *        .addConverterFactory(GsonConverterFactory.create())
 *        .build()
 *    val service = retrofit.create(PriceService::class.java)
 * 
 * 2. Realiza la llamada:
 *    service.getCardPrice(cardName, "TU_API_KEY").enqueue(object : Callback<PriceResponse> {
 *        override fun onResponse(call: Call<PriceResponse>, response: Response<PriceResponse>) {
 *            // Manejar respuesta exitosa
 *        }
 *        override fun onFailure(call: Call<PriceResponse>, t: Throwable) {
 *            // Manejar error
 *        }
 *    })
 * 
 * 3. APIs sugeridas:
 *    - TCGPlayer API: https://docs.tcgplayer.com/
 *    - PokémonTCG API: https://pokemontcg.io/
 *    - eBay API: https://developer.ebay.com/
 */
class FakePriceService {

    /**
     * Simula la obtención del precio de mercado de una carta.
     * Genera valores aleatorios plausibles basados en el nombre de la carta.
     */
    fun getCardPrice(cardName: String): PriceResponse {
        # Simular un pequeño delay como lo haría una API real
        Thread.sleep(500)

        # Generar precios aleatorios basados en hash del nombre (para consistencia)
        val basePrice = (cardName.hashCode() % 100).toDouble().coerceAtLeast(5.0)
        val marketPrice = basePrice + Random.nextDouble(0.0, 20.0)

        return PriceResponse(
            cardName = cardName,
            marketPrice = marketPrice,
            lowPrice = marketPrice * 0.8,
            highPrice = marketPrice * 1.3,
            currency = "USD"
        )
    }

    /**
     * Simula la obtención de precios para múltiples cartas.
     */
    fun getBulkPrices(cardNames: List<String>): List<PriceResponse> {
        return cardNames.map { getCardPrice(it) }
    }
}
