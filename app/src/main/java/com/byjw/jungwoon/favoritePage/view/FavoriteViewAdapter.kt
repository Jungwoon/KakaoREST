package com.byjw.jungwoon.favoritePage.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.byjw.jungwoon.R
import com.byjw.jungwoon.favoritePage.FavoriteContract
import com.byjw.jungwoon.util.retrofit.scheme.BaseContent

class FavoriteViewAdapter
    : RecyclerView.Adapter<FavoriteViewHolder>(),
    FavoriteContract.View {

    private val favoriteList = mutableListOf<BaseContent.Document>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val layoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.contents_card, parent, false)

        return FavoriteViewHolder(itemView = layoutView, view = this)
    }

    override fun getItemCount() = favoriteList.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
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