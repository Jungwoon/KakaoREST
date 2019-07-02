package com.byjw.jungwoon

import com.byjw.jungwoon.util.retrofit.scheme.BaseContent

interface BaseContract {

    interface BasePresenter

    interface BaseView {

        fun addContents(document: BaseContent.Document)

        fun removeContents(document: BaseContent.Document)

        fun clear()

    }


}