package com.byjw.jungwoon.page

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.byjw.jungwoon.R
import com.byjw.jungwoon.page.favorite.FavoriteContract
import com.byjw.jungwoon.retrofit.gson.*
import com.squareup.picasso.Picasso

class CardViewHolder(itemView: View, val view: BaseContract.BaseView) : RecyclerView.ViewHolder(itemView) {

    private val thumbnail: ImageView = itemView.findViewById(R.id.card_image_thumbnail)
    private val title: TextView = itemView.findViewById(R.id.card_text_title)
    private val updateTime: TextView = itemView.findViewById(R.id.card_text_update_time)
    private val favorite: ImageView = itemView.findViewById(R.id.card_favorite)

    fun onBind(baseDocument: BaseContent.Document,
               favoritePresenter: FavoriteContract.Presenter? = null) {

        favorite.setOnClickListener {

            baseDocument.favorite = !baseDocument.favorite

            if (baseDocument.favorite) {
                favorite.setImageResource(R.drawable.ic_favorite_fill)
                favoritePresenter?.addContents(baseDocument)
            }
            else {
                favorite.setImageResource(R.drawable.ic_favorite_border)
                favoritePresenter?.removeContents(baseDocument)
            }
        }

        when (baseDocument) {
            is ImageDocument -> bindImage(baseDocument)
            is VideoDocument -> bindVideo(baseDocument)
        }
    }

    private fun bindImage(imageDocument: ImageDocument) {
        Picasso.get().load(imageDocument.thumbnail_url).into(thumbnail)
        title.text = imageDocument.display_sitename
        updateTime.text = imageDocument.datetime
    }

    private fun bindVideo(videoDocument: VideoDocument) {
        Picasso.get().load(videoDocument.thumbnail).into(thumbnail)
        title.text = videoDocument.title
        updateTime.text = videoDocument.datetime
    }

}
