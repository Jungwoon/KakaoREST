package com.byjw.jungwoon.searchPage.model

import com.byjw.jungwoon.util.retrofit.base.SearchRetrofit
import com.byjw.jungwoon.util.retrofit.scheme.*
import com.byjw.jungwoon.searchPage.SearchContract
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class SearchModel : SearchContract.Model {

    override fun getImageResponseByKeyword(keyword: String, page: Int): List<BaseContent.Document>? {
        val requestSearchImage = SearchRetrofit.getService().requestSearchImage(keyword = keyword, page = page)

        val executor = Executors.newFixedThreadPool(4)

        return (executor.submit(Callable {
            return@Callable requestSearchImage.execute().body()?.documents
        })).get()
    }

    override fun getVideoResponseByKeyword(keyword: String, page: Int): List<BaseContent.Document>? {
        val requestSearchVideo = SearchRetrofit.getService().requestSearchVideo(keyword = keyword, page = page)

        val executor = Executors.newFixedThreadPool(4)

        return (executor.submit(Callable {
            return@Callable requestSearchVideo.execute().body()?.documents
        })).get()
    }

}
