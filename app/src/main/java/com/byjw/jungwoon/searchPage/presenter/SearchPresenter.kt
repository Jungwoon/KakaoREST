package com.byjw.jungwoon.searchPage.presenter

import android.util.Log
import com.byjw.jungwoon.util.retrofit.scheme.BaseContent
import com.byjw.jungwoon.util.retrofit.scheme.kakaoApi.ImageDocument
import com.byjw.jungwoon.util.retrofit.scheme.kakaoApi.VideoDocument
import com.byjw.jungwoon.searchPage.SearchContract
import com.byjw.jungwoon.util.retrofit.scheme.SortedDocument
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.io.Serializable

class SearchPresenter(
    private val model: SearchContract.Model,
    val view: SearchContract.View
) : SearchContract.Presenter, Serializable {

    override fun addSearchResponseByKeyword(keyword: String, page: Int) {
        val imageResponse = model.getImageResponseByKeyword(keyword, page)
        val videoResponse = model.getVideoResponseByKeyword(keyword, page)

        Observable
            .zip(
                imageResponse,
                videoResponse,
                BiFunction <List<BaseContent.Document>, List<BaseContent.Document>,
                        MutableList<SortedDocument>> {image, video ->

                    val combineList = mutableListOf<SortedDocument>()
                    addCombineList(combineList = combineList, documentList = image)
                    addCombineList(combineList = combineList, documentList = video)

                    combineList.sortByDescending { it.date }

                    return@BiFunction combineList
                }
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                Log.e("JW_TEST", "Throwable : $it")
            }
            .subscribe(view::addSortedList)
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
