package com.byjw.jungwoon.searchPage.model

import com.byjw.jungwoon.util.retrofit.base.SearchRetrofit
import com.byjw.jungwoon.util.retrofit.scheme.*
import com.byjw.jungwoon.searchPage.SearchContract
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class SearchModel : SearchContract.Model {

    override fun getImageResponseByKeyword(keyword: String, page: Int): Observable<List<BaseContent.Document>>? {
        try {
            return SearchRetrofit.getService().requestSearchImage(keyword = keyword, page = page)
                .subscribeOn(Schedulers.io())
                .flatMap {
                    return@flatMap Observable.just(it.documents)
                }
        } catch(e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    override fun getVideoResponseByKeyword(keyword: String, page: Int): Observable<List<BaseContent.Document>>? {
        try {
            return SearchRetrofit.getService().requestSearchVideo(keyword = keyword, page = page)
                .subscribeOn(Schedulers.io())
                .flatMap {
                    return@flatMap Observable.just(it.documents)
                }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

}
