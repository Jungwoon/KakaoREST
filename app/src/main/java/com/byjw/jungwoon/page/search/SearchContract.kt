package com.byjw.jungwoon.page.search

import com.byjw.jungwoon.page.BaseContract
import com.byjw.jungwoon.retrofit.gson.BaseContent

interface SearchContract {

    interface Model {

        fun getImageResponseByKeyword(keyword: String): List<BaseContent.Document>?

        fun getVideoResponseByKeyword(keyword: String): List<BaseContent.Document>?

    }

    interface View : BaseContract.BaseView {

        fun addSortedList(sortedDocuments: List<SortedDocument>)

        override fun addContents(document: BaseContent.Document)

        override fun removeContents(document: BaseContent.Document)

        override fun clear()

    }

    interface Presenter : BaseContract.BasePresenter {

        fun addSearchResponseByKeyword(keyword: String)

    }
}