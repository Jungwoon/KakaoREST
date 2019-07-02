package com.byjw.jungwoon.page.favorite

import com.byjw.jungwoon.page.BaseContract
import com.byjw.jungwoon.retrofit.gson.BaseContent

interface FavoriteContract {

    interface View : BaseContract.BaseView {

        override fun addContents(document: BaseContent.Document)

        override fun removeContents(document: BaseContent.Document)

        override fun clear()
    }

    interface Presenter : BaseContract.BasePresenter {

        fun addContents(document: BaseContent.Document)

        fun removeContents(document: BaseContent.Document)

        fun clear()

    }

}