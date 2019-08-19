package com.byjw.jungwoon.util

import com.byjw.jungwoon.util.retrofit.scheme.BaseContent
import io.reactivex.subjects.PublishSubject

object RxEventBus {

    val subjectSearchKeyword = PublishSubject.create<String>()
    val subjectAddFavorite = PublishSubject.create<BaseContent.Document>()
    val subjectRemoveFavorite = PublishSubject.create<BaseContent.Document>()
    val subjectUnlikeSearch = PublishSubject.create<BaseContent.Document>()

    fun searchKeyword(keyword: String) {
        subjectSearchKeyword.onNext(keyword)
    }

    fun addFavorite(document: BaseContent.Document) {
        subjectAddFavorite.onNext(document)
    }

    fun removeFavorite(document: BaseContent.Document) {
        subjectRemoveFavorite.onNext(document)
    }

    fun unlikeSearch(document: BaseContent.Document) {
        subjectUnlikeSearch.onNext(document)
    }


}