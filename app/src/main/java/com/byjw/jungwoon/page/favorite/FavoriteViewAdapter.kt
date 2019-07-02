package com.byjw.jungwoon.page.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.byjw.jungwoon.page.CardViewHolder
import com.byjw.jungwoon.R
import com.byjw.jungwoon.retrofit.gson.BaseContent
import java.io.Serializable

class FavoriteViewAdapter
    : RecyclerView.Adapter<CardViewHolder>(), FavoriteContract.View {

    private val favoriteList = mutableListOf<BaseContent.Document>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.contents_card, parent, false)

        return CardViewHolder(itemView = layoutView, view = this)
    }

    override fun getItemCount() = favoriteList.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.onBind(favoriteList[position])
    }

    override fun addContents(document: BaseContent.Document) {
        favoriteList.add(document)
        notifyDataSetChanged()
    }

    override fun removeContents(document: BaseContent.Document) {
        favoriteList.remove(document)
        notifyDataSetChanged()
    }

    override fun clear() {
        favoriteList.clear()
        notifyDataSetChanged()
    }

}