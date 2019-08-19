package com.byjw.jungwoon.favoritePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.byjw.jungwoon.favoritePage.presenter.FavoritePresenter
import com.byjw.jungwoon.favoritePage.view.FavoriteViewAdapter
import com.byjw.jungwoon.util.RxEventBus
import kotlinx.android.synthetic.main.fragment_favorite.view.*


class FavoriteFragment : Fragment() {

    private lateinit var favoritePresenter: FavoriteContract.Presenter

    override fun onResume() {
        super.onResume()

        favoritePresenter.addDisposable(
            RxEventBus.subjectAddFavorite.subscribe(favoritePresenter::addContents)
        )

        favoritePresenter.addDisposable(
            RxEventBus.subjectRemoveFavorite.subscribe(favoritePresenter::removeContents)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(com.byjw.jungwoon.R.layout.fragment_favorite, container, false)

        val favoriteViewAdapter = FavoriteViewAdapter(fragmentView = view)

        favoritePresenter = FavoritePresenter(favoriteViewAdapter)

        view.favorite_recycler_view.setHasFixedSize(true)
        view.favorite_recycler_view.layoutManager = LinearLayoutManager(context)
        view.favorite_recycler_view.adapter = favoriteViewAdapter

        return view
    }

    override fun onDestroy() {
        super.onDestroy()

        favoritePresenter.dispose()
    }

}
