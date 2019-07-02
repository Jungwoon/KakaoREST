package com.byjw.jungwoon.page.favorite

import com.byjw.jungwoon.retrofit.gson.BaseContent
import java.io.Serializable

class FavoritePresenter(val view: FavoriteContract.View) : FavoriteContract.Presenter, Serializable {
    
    override fun addContents(document: BaseContent.Document) {
        view.addContents(document)
    }

    override fun removeContents(document: BaseContent.Document) {
        view.removeContents(document)
    }

    override fun clear() {
        view.clear()
    }

}
