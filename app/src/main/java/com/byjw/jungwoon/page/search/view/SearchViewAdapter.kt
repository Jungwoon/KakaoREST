package com.byjw.jungwoon.page.search.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.byjw.jungwoon.R
import com.byjw.jungwoon.page.CardViewHolder
import com.byjw.jungwoon.page.favorite.FavoriteContract
import com.byjw.jungwoon.page.favorite.FavoritePresenter
import com.byjw.jungwoon.retrofit.gson.BaseContent
import com.byjw.jungwoon.page.search.SearchContract
import com.byjw.jungwoon.page.search.SortedDocument
import kotlinx.android.synthetic.main.contents_card.view.*
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import java.io.Serializable

class SearchViewAdapter(private val favoritePresenter: FavoriteContract.Presenter)
    : RecyclerView.Adapter<CardViewHolder>(), SearchContract.View {

    private val contentsList = mutableListOf<BaseContent.Document>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.contents_card, parent, false)

        return CardViewHolder(itemView = layoutView, view = this)
    }

    override fun getItemCount() = contentsList.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.onBind(contentsList[position], favoritePresenter)
    }

    override fun addContents(document: BaseContent.Document) {
        contentsList.add(document)
        notifyDataSetChanged()
    }

    override fun removeContents(document: BaseContent.Document) {
        contentsList.remove(document)
        notifyDataSetChanged()
    }

    override fun addSortedList(sortedDocuments: List<SortedDocument>) {
        for (sortedDocument in sortedDocuments) {
            contentsList.add(sortedDocument.document)
        }

        notifyDataSetChanged()
    }

    override fun clear() {
        contentsList.clear()
    }
}