package com.example.appcartaspokemon

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appcartaspokemon.databinding.ActivityAddCardBinding
import com.example.appcartaspokemon.viewmodel.CardViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * Actividad para agregar una nueva carta a la colección.
 */
class AddCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCardBinding
    private val viewModel: CardViewModel by viewModels()

    // Opciones predefinidas para spinners
    private val pokemonTypes = arrayOf(
        "Fuego", "Agua", "Planta", "Eléctrico", "Psíquico",
        "Lucha", "Oscuridad", "Metal", "Dragón", "Hada",
        "Normal", "Volador", "Veneno", "Tierra", "Roca",
        "Bicho", "Fantasma", "Hielo"
    )

    private val cardQualities = arrayOf(
        "Mint", "Near Mint", "Excellent", "Good",
        "Light Played", "Played", "Poor"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupSpinners()
        setupButtons()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Agregar Nueva Carta"
        }
    }

    private fun setupSpinners() {
        // Configurar Spinner de tipos
        val typeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, pokemonTypes)
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerReintentarClaude aún no tiene la capacidad de ejecutar el código que genera.NContinuarType.adapter = typeAdapter
    // Configurar Spinner de calidades
    val qualityAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cardQualities)
    qualityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    binding.spinnerQuality.adapter = qualityAdapter
}

private fun setupButtons() {
    binding.btnSaveCard.setOnClickListener {
        saveCard()
    }

    binding.btnCancel.setOnClickListener {
        finish()
    }
}

private fun saveCard() {
    // Obtener valores del formulario
    val name = binding.etCardName.text.toString().trim()
    val type = binding.spinnerType.selectedItem.toString()
    val quality = binding.spinnerQuality.selectedItem.toString()
    val priceText = binding.etCardPrice.text.toString().trim()
    val imageUrl = binding.etCardImageUrl.text.toString().trim()

    // Validaciones
    if (name.isEmpty()) {
        binding.etCardName.error = "El nombre es obligatorio"
        binding.etCardName.requestFocus()
        return
    }

    if (priceText.isEmpty()) {
        binding.etCardPrice.error = "El precio es obligatorio"
        binding.etCardPrice.requestFocus()
        return
    }

    val price = priceText.toDoubleOrNull()
    if (price == null || price < 0) {
        binding.etCardPrice.error = "Ingresa un precio válido (mayor o igual a 0)"
        binding.etCardPrice.requestFocus()
        return
    }

    // Agregar la carta
    viewModel.addCard(name, type, quality, price, imageUrl)

    // Mostrar confirmación y cerrar
    Snackbar.make(binding.root, "Carta agregada exitosamente", Snackbar.LENGTH_SHORT).show()

    // Establecer resultado exitoso y cerrar
    setResult(RESULT_OK)
    finish()
}

override fun onSupportNavigateUp(): Boolean {
    onBackPressed()
    return true
}
}
