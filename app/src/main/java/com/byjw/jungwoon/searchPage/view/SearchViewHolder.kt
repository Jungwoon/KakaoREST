package com.byjw.jungwoon.searchPage.view

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.byjw.jungwoon.util.otto.BusProvider
import com.byjw.jungwoon.util.otto.event.BusEventAddToFavorite
import com.byjw.jungwoon.util.otto.event.BusEventRemoveToFavorite
import com.byjw.jungwoon.BaseContract
import com.byjw.jungwoon.R
import com.byjw.jungwoon.util.retrofit.scheme.*
import com.byjw.jungwoon.util.retrofit.scheme.kakaoApi.ImageDocument
import com.byjw.jungwoon.util.retrofit.scheme.kakaoApi.VideoDocument
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


class SearchViewHolder(itemView: View, val view: BaseContract.BaseView) : RecyclerView.ViewHolder(itemView) {

    private val thumbnail: ImageView = itemView.findViewById(R.id.card_image_thumbnail)
    private val title: TextView = itemView.findViewById(R.id.card_text_title)
    private val updateTime: TextView = itemView.findViewById(R.id.card_text_update_time)
    private val favorite: ImageView = itemView.findViewById(R.id.card_favorite)

    fun onBind(document: BaseContent.Document) {

        favorite.setOnClickListener {

            document.favorite = !document.favorite

            if (document.favorite) {
                favorite.setImageResource(R.drawable.ic_favorite_fill)
                BusProvider.post(BusEventAddToFavorite(document))
            } else {
                favorite.setImageResource(R.drawable.ic_favorite_border)
                BusProvider.post(BusEventRemoveToFavorite(document))
            }
        }

        when (document) {
            is ImageDocument -> bindImage(document)
            is VideoDocument -> bindVideo(document)
        }
    }

    private fun bindImage(imageDocument: ImageDocument) {
        Picasso.get().load(imageDocument.thumbnail_url).into(thumbnail)
        title.text = imageDocument.display_sitename
        updateTime.text = convertISO8601ToDate(imageDocument.datetime)
        switchFavoriteImage(imageDocument)
    }

    private fun bindVideo(videoDocument: VideoDocument) {
        Picasso.get().load(videoDocument.thumbnail).into(thumbnail)
        title.text = videoDocument.title
        updateTime.text = convertISO8601ToDate(videoDocument.datetime)
        switchFavoriteImage(videoDocument)
    }

    private fun switchFavoriteImage(document: BaseContent.Document) {
        if (document.favorite) {
            favorite.setImageResource(R.drawable.ic_favorite_fill)
        } else {
            favorite.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    private fun convertISO8601ToDate(dateStr: String): String {
        val formatISO8601 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.KOREAN)
        val date = formatISO8601.parse(dateStr)
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREAN).format(date)
    }

}
