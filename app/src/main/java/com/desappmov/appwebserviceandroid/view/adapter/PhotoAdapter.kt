package com.desappmov.appwebserviceandroid.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.desappmov.appwebserviceandroid.R
import com.desappmov.appwebserviceandroid.model.Photo

class PhotoAdapter(
    private var photoList: List<Photo>,
    private val onItemClick: (Photo) -> Unit
    // ,
    // private val onEdit: (Photo) -> Unit,
    // private val onDelete: (Photo) -> Unit,
) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    // ViewHolder
    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.photoImageView)
        val viewsText: TextView = itemView.findViewById(R.id.viewsTextView)
        val downloadsText: TextView = itemView.findViewById(R.id.downloadsTextView)
        val detailButton: Button = itemView.findViewById(R.id.detailButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int = photoList.size

    /*
    override fun getItemCount(): Int {
        return photoList.size
    }
     */

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photoList[position]

        // Cargar imagen con Coil
        holder.imageView.load(photo.largeImageURL) {
            crossfade(true)
            placeholder(R.drawable.ic_placeholder) // opcional: imagen de espera
        }

        holder.viewsText.text = "Vistas: ${photo.views}"
        holder.downloadsText.text = "Descargas: ${photo.downloads}"

        // Botón "Ver más"
        holder.detailButton.setOnClickListener {
            onItemClick(photo)
        }
    }

    // Método para actualizar los datos
    fun updatePhotos(newPhotos: List<Photo>) {
        photoList = newPhotos
        notifyDataSetChanged()
    }

}