package com.byjw.jungwoon.searchPage.presenter

import android.util.Log
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
        val imageResponse = model.getImageResponseByKeyword(keyword, page)
        val videoResponse = model.getVideoResponseByKeyword(keyword, page)

        if (imageResponse == null || videoResponse == null) {
            view.toast("네트워크에 문제가 발생하였습니다. 잠시 후 다시 시도해주시기 바랍니다.")
        } else {
            if (imageResponse.isEmpty() && videoResponse.isEmpty()) {
                view.toast("검색하신 키워드에 대한 결과를 찾지 못하였습니다.")
            } else {
                val combineList = mutableListOf<SortedDocument>()
                addCombineList(combineList, imageResponse)
                addCombineList(combineList, videoResponse)
                combineList.sortByDescending { it.date }

                view.addSortedList(combineList)
            }
        }
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
