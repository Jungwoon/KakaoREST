package com.byjw.jungwoon.searchPage

import com.byjw.jungwoon.BaseContract
import com.byjw.jungwoon.util.retrofit.scheme.BaseContent
import com.byjw.jungwoon.util.retrofit.scheme.SortedDocument

interface SearchContract {

    interface Model {

        fun getImageResponseByKeyword(keyword: String): List<BaseContent.Document>?

        fun getVideoResponseByKeyword(keyword: String): List<BaseContent.Document>?

    }

    interface View : BaseContract.BaseView {

        override fun addContents(document: BaseContent.Document)

        override fun removeContents(document: BaseContent.Document)

        override fun clear()

        fun addSortedList(sortedDocuments: List<SortedDocument>)

        fun unlike(document: BaseContent.Document)

    }

    interface Presenter : BaseContract.BasePresenter {

        fun addSearchResponseByKeyword(keyword: String)

        fun unlike(document: BaseContent.Document)

    }
}