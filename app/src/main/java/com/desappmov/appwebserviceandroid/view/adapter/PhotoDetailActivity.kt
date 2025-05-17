package com.desappmov.appwebserviceandroid.view.adapter

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import com.desappmov.appwebserviceandroid.R
import com.desappmov.appwebserviceandroid.model.Photo

class PhotoDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_photo_detail)

        // val idPhoto = intent.getIntExtra("idPhoto").toInt() ?: return
        val photo = intent.getParcelableExtra<Photo>("photo") ?: return
        // val photo = intent.getParcelableExtra("photo", Photo::class.java) ?: return

        // Referencias
        val detailImageView: ImageView = findViewById(R.id.detailImageView)
        val tagsTextView: TextView = findViewById(R.id.tagsTextView)
        val viewsTextView: TextView = findViewById(R.id.viewsTextView)
        val downloadsTextView: TextView = findViewById(R.id.downloadsTextView)
        val likesTextView: TextView = findViewById(R.id.likesTextView)
        val commentsTextView: TextView = findViewById(R.id.commentsTextView)
        val userImageView: ImageView = findViewById(R.id.userImageView)
        val usernameTextView: TextView = findViewById(R.id.usernameTextView)

        // Asignar datos
        detailImageView.load(photo.largeImageURL)
        tagsTextView.text = photo.tags
        viewsTextView.text = "Vistas: ${photo.views}"
        downloadsTextView.text = "Descargas: ${photo.downloads}"
        likesTextView.text = "Likes: ${photo.likes}"
        commentsTextView.text = "Comentarios: ${photo.comments}"
        usernameTextView.text = photo.user
        userImageView.load(photo.userImageURL)

    }
}