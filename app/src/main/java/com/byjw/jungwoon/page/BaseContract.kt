package com.byjw.jungwoon.page

import com.byjw.jungwoon.retrofit.gson.BaseContent
import java.io.Serializable

interface BaseContract {

    interface BasePresenter : Serializable

    interface BaseView {

        fun addContents(document: BaseContent.Document)

        fun removeContents(document: BaseContent.Document)

        fun clear()

    }


}