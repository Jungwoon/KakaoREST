package com.byjw.jungwoon.searchPage.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.byjw.jungwoon.R
import com.byjw.jungwoon.util.retrofit.scheme.BaseContent
import com.byjw.jungwoon.searchPage.SearchContract
import com.byjw.jungwoon.util.retrofit.scheme.SortedDocument
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchViewAdapter(val fragmentView: View)
    : RecyclerView.Adapter<SearchViewHolder>(), SearchContract.View {

    private val searchList = mutableListOf<BaseContent.Document>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.contents_card, parent, false)

        return SearchViewHolder(itemView = layoutView, view = this)
    }

    override fun getItemCount() = searchList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.onBind(searchList[position])
    }

    override fun addContents(document: BaseContent.Document) {
        searchList.add(document)
        update()
    }

    override fun removeContents(document: BaseContent.Document) {
        searchList.remove(document)
        update()
    }

    override fun addSortedList(sortedDocuments: List<SortedDocument>) {
        for (sortedDocument in sortedDocuments) {
            searchList.add(sortedDocument.document)
        }

        update()
    }

    override fun clear() {
        searchList.clear()
    }

    override fun unlike(document: BaseContent.Document) {
        document.favorite = false
        update()
    }

    override fun toast(msg: String) {
        Toast.makeText(fragmentView.context, msg, Toast.LENGTH_LONG).show()
    }

    private fun update() {
        notifyDataSetChanged()

        val emptyLayout = fragmentView.search_layout_empty

        if (searchList.isEmpty()) {
            emptyLayout.visibility = View.VISIBLE
        } else {
            emptyLayout.visibility = View.GONE
        }
    }

}