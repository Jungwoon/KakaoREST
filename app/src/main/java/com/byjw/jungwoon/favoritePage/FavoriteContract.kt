package com.byjw.jungwoon.favoritePage

import android.widget.LinearLayout
import com.byjw.jungwoon.BaseContract
import com.byjw.jungwoon.util.retrofit.scheme.BaseContent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface FavoriteContract {

    interface View : BaseContract.BaseView {

        override fun addContents(document: BaseContent.Document)

        override fun removeContents(document: BaseContent.Document)

        override fun clear()

    }

    interface Presenter : BaseContract.BasePresenter {

        val compositeDisposable: CompositeDisposable

        fun addContents(document: BaseContent.Document)

        fun removeContents(document: BaseContent.Document)

        fun clear()

        fun addDisposable(disposable: Disposable)

        fun dispose()

    }

}