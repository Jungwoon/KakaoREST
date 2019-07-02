package com.byjw.jungwoon.favoritePage

import com.byjw.jungwoon.BaseContract
import com.byjw.jungwoon.util.retrofit.scheme.BaseContent

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