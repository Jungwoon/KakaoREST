package com.byjw.jungwoon.page.search.presenter

import com.byjw.jungwoon.page.favorite.FavoriteContract
import com.byjw.jungwoon.retrofit.gson.BaseContent
import com.byjw.jungwoon.retrofit.gson.ImageDocument
import com.byjw.jungwoon.retrofit.gson.VideoDocument
import com.byjw.jungwoon.page.search.SearchContract
import com.byjw.jungwoon.page.search.SortedDocument
import java.io.Serializable

class SearchPresenter(
    val model: SearchContract.Model,
    val view: SearchContract.View
) : SearchContract.Presenter, Serializable {

    override fun addSearchResponseByKeyword(keyword: String) {
        val combineList = mutableListOf<SortedDocument>()

        addCombineList(combineList, model.getImageResponseByKeyword(keyword)!!)
        addCombineList(combineList, model.getVideoResponseByKeyword(keyword)!!)
        combineList.sortByDescending { it.date }

        view.addSortedList(combineList)
    }

    private fun addCombineList(
        combineList: MutableList<SortedDocument>,
        documentList: List<BaseContent.Document>
    ): MutableList<SortedDocument> {

        for (document in documentList) {
            when(document) {
                is VideoDocument -> combineList.add(
                    SortedDocument(
                        date = document.datetime,
                        document = document
                    )
                )
                is ImageDocument -> combineList.add(
                    SortedDocument(
                        date = document.datetime,
                        document = document
                    )
                )
            }
        }

        return combineList
    }
}
