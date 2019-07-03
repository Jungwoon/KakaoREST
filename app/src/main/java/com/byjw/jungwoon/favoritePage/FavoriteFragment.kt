package com.byjw.jungwoon.favoritePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.byjw.jungwoon.R
import com.byjw.jungwoon.util.otto.BusProvider
import com.byjw.jungwoon.util.otto.event.BusEventAddToFavorite
import com.byjw.jungwoon.util.otto.event.BusEventRemoveToFavorite
import com.byjw.jungwoon.favoritePage.presenter.FavoritePresenter
import com.byjw.jungwoon.favoritePage.view.FavoriteViewAdapter
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : Fragment() {

    private lateinit var favoritePresenter: FavoriteContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BusProvider.register(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        val favoriteViewAdapter = FavoriteViewAdapter(fragmentView = view)

        favoritePresenter = FavoritePresenter(favoriteViewAdapter)

        view.favorite_recycler_view.setHasFixedSize(true)
        view.favorite_recycler_view.layoutManager = LinearLayoutManager(context)
        view.favorite_recycler_view.adapter = favoriteViewAdapter

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        BusProvider.unregister(this)
    }

    @Subscribe
    fun addBus(busEventAddToFavorite: BusEventAddToFavorite) {
        favoritePresenter.addContents(busEventAddToFavorite.document)
    }

    @Subscribe
    fun removeBus(busEventRemoveToFavorite: BusEventRemoveToFavorite) {
        favoritePresenter.removeContents(busEventRemoveToFavorite.document)
    }

}
