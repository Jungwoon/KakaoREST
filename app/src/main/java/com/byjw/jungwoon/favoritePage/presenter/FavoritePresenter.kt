package com.byjw.jungwoon.favoritePage.presenter

import com.byjw.jungwoon.favoritePage.FavoriteContract
import com.byjw.jungwoon.util.retrofit.scheme.BaseContent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class FavoritePresenter(val view: FavoriteContract.View) :
    FavoriteContract.Presenter {

    override val compositeDisposable = CompositeDisposable()

    override fun addContents(document: BaseContent.Document) {
        view.addContents(document)
    }

    override fun removeContents(document: BaseContent.Document) {
        view.removeContents(document)
    }

    override fun clear() {
        view.clear()
    }

    override fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun dispose() {
        compositeDisposable.dispose()
    }

}
