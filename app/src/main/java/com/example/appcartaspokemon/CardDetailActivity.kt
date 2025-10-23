package com.example.appcartaspokemon

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.appcartaspokemon.databinding.ActivityCardDetailBinding
import com.example.appcartaspokemon.model.Card
import com.google.android.material.snackbar.Snackbar

/**
 * Actividad que muestra los detalles completos de una carta.
 */
class CardDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCardDetailBinding
    private lateinit var card: Card

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener la carta desde el Intent
        card = intent.getParcelableExtra("CARD") ?: run {
            finish()
            return
        }

        setupToolbar()
        displayCardDetails()
        setupButtons()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = card.name
        }
    }

    private fun displayCardDetails() {
        binding.apply {
            // Cargar imagen grande
            Glide.with(this@CardDetailActivity)
                .load(card.imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(ivCardDetailImage)

            tvDetailName.text = card.name
            tvDetailType.text = "Tipo: ${card.type}"
            tvDetailQuality.text = "Calidad: ${card.quality}"
            tvDetailPrice.text = card.getFormattedPrice()

            // Mostrar descripción si existe
            if (card.description.isNotEmpty()) {
                tvDetailDescription.text = card.description
            } else {
                tvDetailDescription.text = "Esta carta no tiene descripción disponible."
            }
        }
    }

    private fun setupButtons() {
        // Botón para copiar URL
        binding.btnCopyUrl.setOnClickListener {
            copyToClipboard(card.imageUrl)
        }

        // Botón para compartir
        binding.btnShareCard.setOnClickListener {
            shareCard()
        }
    }

    private fun copyToClipboard(text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("URL de Carta", text)
        clipboard.setPrimaryClip(clip)

        Snackbar.make(
            binding.root,
            "URL copiada al portapapeles",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun shareCard() {
        val shareText = """
            ¡Mira esta carta de Pokémon!

            Nombre: ${card.name}
            Tipo: ${card.type}
            Calidad: ${card.quality}
            Precio: ${card.getFormattedPrice()}

            Imagen: ${card.imageUrl}
        """.trimIndent()

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "Compartir carta")
        startActivity(shareIntent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
