package com.desappmov.appwebserviceandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desappmov.appwebserviceandroid.model.Photo
import com.desappmov.appwebserviceandroid.network.PixabayRepository
import kotlinx.coroutines.launch

class PhotoViewModel : ViewModel() {

    private val repository = PixabayRepository()

    // LiveData para lista de fotos
    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> get() = _photos

    // LiveData para mostrar carga
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    // LiveData para errores (opcional)
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    init {
        // Cargar fotos por defecto al iniciar
        fetchPhotos("", "all")
    }

    fun fetchPhotos(query: String, orientation: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = repository.getPhotos(query, orientation)
                _photos.value = response.hits
            } catch (e: Exception) {
                _error.value = "Error al obtener datos: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

}