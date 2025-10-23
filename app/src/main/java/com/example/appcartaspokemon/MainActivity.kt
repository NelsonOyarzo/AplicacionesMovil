package com.example.appcartaspokemon

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appcartaspokemon.adapter.CardAdapter
import com.example.appcartaspokemon.databinding.ActivityMainBinding
import com.example.appcartaspokemon.model.Card
import com.example.appcartaspokemon.viewmodel.CardViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * Actividad principal que muestra la lista de cartas en un RecyclerView.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cardAdapter: CardAdapter
    private val viewModel: CardViewModel by viewModels()

    companion object {
        const val ADD_CARD_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        setupFab()
        observeViewModel()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Mis Cartas Pokémon"
    }

    private fun setupRecyclerView() {
        // Configurar adapter con listener de clicks
        cardAdapter = CardAdapter(emptyList()) { card ->
            openCardDetail(card)
        }

        // Usar GridLayoutManager para mostrar 2 columnas
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = cardAdapter
        }
    }

    private fun setupFab() {
        binding.fabAddCard.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            startActivityForResult(intent, ADD_CARD_REQUEST)
        }
    }

    private fun observeViewModel() {
        viewModel.cards.observe(this) { cards ->
            cardAdapter.updateCards(cards)

            // Mostrar mensaje si no hay cartas
            if (cards.isEmpty()) {
                Snackbar.make(
                    binding.root,
                    "No hay cartas. ¡Agrega tu primera carta!",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        // Configurar SearchView
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.queryHint = "Buscar por nombre, tipo o calidad..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                cardAdapter.filter.filter(newText)
                return true
            }
        })

        return true
    }

    private fun openCardDetail(card: Card) {
        val intent = Intent(this, CardDetailActivity::class.java).apply {
            putExtra("CARD", card)
        }
        startActivity(intent)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_CARD_REQUEST && resultCode == RESULT_OK) {
            // Recargar las cartas después de agregar una nueva
            viewModel.loadCards()

            Snackbar.make(
                binding.root,
                "¡Carta agregada exitosamente!",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}
