package com.byjw.jungwoon.searchPage.presenter

import com.byjw.jungwoon.util.retrofit.scheme.BaseContent
import com.byjw.jungwoon.util.retrofit.scheme.kakaoApi.ImageDocument
import com.byjw.jungwoon.util.retrofit.scheme.kakaoApi.VideoDocument
import com.byjw.jungwoon.searchPage.SearchContract
import com.byjw.jungwoon.util.retrofit.scheme.SortedDocument
import java.io.Serializable

class SearchPresenter(
    private val model: SearchContract.Model,
    val view: SearchContract.View
) : SearchContract.Presenter, Serializable {

    override fun addSearchResponseByKeyword(keyword: String, page: Int) {
        val combineList = mutableListOf<SortedDocument>()

        addCombineList(combineList, model.getImageResponseByKeyword(keyword, page)!!)
        addCombineList(combineList, model.getVideoResponseByKeyword(keyword, page)!!)
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

    override fun clear() {
        view.clear()
    }

    override fun unlike(document: BaseContent.Document) {
        view.unlike(document)
    }

}
