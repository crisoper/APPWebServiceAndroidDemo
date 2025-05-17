package com.desappmov.appwebserviceandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.desappmov.appwebserviceandroid.model.Photo
import com.desappmov.appwebserviceandroid.view.adapter.PhotoAdapter
import com.desappmov.appwebserviceandroid.view.adapter.PhotoDetailActivity
import com.desappmov.appwebserviceandroid.viewmodel.PhotoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PhotoViewModel
    private lateinit var adapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Referencias a vistas
        val searchEditText: EditText = findViewById(R.id.searchEditText)
        val orientationSpinner: Spinner = findViewById(R.id.orientationSpinner)
        val searchButton: Button = findViewById(R.id.searchButton)
        val recyclerView: RecyclerView = findViewById(R.id.photosRecyclerView)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        // Spinner de orientación
        val orientations = listOf("all", "horizontal", "vertical")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, orientations)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        orientationSpinner.adapter = spinnerAdapter

        // ViewModel
        viewModel = ViewModelProvider(this)[PhotoViewModel::class.java]

        // RecyclerView
        adapter = PhotoAdapter(emptyList()) { photo ->
            goToDetail(photo)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Observadores
        viewModel.photos.observe(this) { photos ->
            adapter.updatePhotos(photos)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }


        viewModel.error.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        // Botón buscar
        searchButton.setOnClickListener {
            val query = searchEditText.text.toString().trim()
            val orientation = orientationSpinner.selectedItem.toString()
            viewModel.fetchPhotos(query, orientation)
        }

    }


    // Ir al detalle
    private fun goToDetail(photo: Photo) {
        val intent = Intent(this, PhotoDetailActivity::class.java).apply {
            putExtra("photo", photo)
            // putExtra("idPhoto", photo.id.toInt())
        }
        startActivity(intent)
    }

}