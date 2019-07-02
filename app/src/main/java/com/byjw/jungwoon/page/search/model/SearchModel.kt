package com.byjw.jungwoon.page.search.model

import com.byjw.jungwoon.retrofit.base.SearchRetrofit
import com.byjw.jungwoon.retrofit.gson.*
import com.byjw.jungwoon.page.search.SearchContract
import java.io.Serializable
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class SearchModel : SearchContract.Model {

    override fun getImageResponseByKeyword(keyword: String): List<BaseContent.Document>? {
        val requestSearchImage = SearchRetrofit.getService().requestSearchImage(keyword)

        val executor = Executors.newFixedThreadPool(4)

        return (executor.submit(Callable {
            return@Callable requestSearchImage.execute().body()?.documents
        })).get()
    }

    override fun getVideoResponseByKeyword(keyword: String): List<BaseContent.Document>? {
        val requestSearchVideo = SearchRetrofit.getService().requestSearchVideo(keyword)

        val executor = Executors.newFixedThreadPool(4)

        return (executor.submit(Callable {
            return@Callable requestSearchVideo.execute().body()?.documents
        })).get()
    }

}
