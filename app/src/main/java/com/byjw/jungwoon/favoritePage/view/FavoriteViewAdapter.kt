package com.byjw.jungwoon.favoritePage.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.byjw.jungwoon.R
import com.byjw.jungwoon.favoritePage.FavoriteContract
import com.byjw.jungwoon.util.retrofit.scheme.BaseContent
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import kotlinx.android.synthetic.main.fragment_search.view.*

class FavoriteViewAdapter(val fragmentView: View)
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
        update()
    }

    override fun removeContents(document: BaseContent.Document) {
        favoriteList.remove(document)
        update()
    }

    override fun clear() {
        favoriteList.clear()
        update()
    }

    private fun update() {
        notifyDataSetChanged()

        val emptyLayout = fragmentView.favorite_layout_empty

        if (favoriteList.isEmpty()) {
            emptyLayout.visibility = View.VISIBLE
        } else {
            emptyLayout.visibility = View.GONE
        }
    }


}