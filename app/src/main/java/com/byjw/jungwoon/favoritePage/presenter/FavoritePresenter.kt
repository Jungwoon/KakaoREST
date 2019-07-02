package com.byjw.jungwoon.favoritePage.presenter

import com.byjw.jungwoon.favoritePage.FavoriteContract
import com.byjw.jungwoon.util.retrofit.scheme.BaseContent

class FavoritePresenter(val view: FavoriteContract.View) :
    FavoriteContract.Presenter {

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
