package com.byjw.jungwoon.searchPage.model

import com.byjw.jungwoon.util.retrofit.base.SearchRetrofit
import com.byjw.jungwoon.util.retrofit.scheme.*
import com.byjw.jungwoon.searchPage.SearchContract
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class SearchModel : SearchContract.Model {

    override fun getImageResponseByKeyword(keyword: String, page: Int): List<BaseContent.Document>? {
        try {
            val requestSearchImage = SearchRetrofit.getService().requestSearchImage(keyword = keyword, page = page)
            val executor = Executors.newFixedThreadPool(4)

            return (executor.submit(Callable {
                val response = requestSearchImage.execute()

                if (response.isSuccessful) {
                    return@Callable response.body()?.documents
                } else {
                    return@Callable null
                }

            })).get()

        } catch(e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    override fun getVideoResponseByKeyword(keyword: String, page: Int): List<BaseContent.Document>? {
        try {
            val requestSearchVideo = SearchRetrofit.getService().requestSearchVideo(keyword = keyword, page = page)
            val executor = Executors.newFixedThreadPool(4)

            return (executor.submit(Callable {
                val response = requestSearchVideo.execute()

                if (response.isSuccessful) {
                    return@Callable response.body()?.documents
                } else {
                    return@Callable null
                }
            })).get()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

}
