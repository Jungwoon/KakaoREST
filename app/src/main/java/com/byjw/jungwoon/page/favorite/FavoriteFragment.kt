package com.byjw.jungwoon.page.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.byjw.jungwoon.R
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        val favoritePresenter = arguments?.getSerializable("presenter") as FavoritePresenter

        view.favorite_recycler_view.setHasFixedSize(true)
        view.favorite_recycler_view.layoutManager = LinearLayoutManager(context)
        view.favorite_recycler_view.adapter = favoritePresenter.view as FavoriteViewAdapter

        return view
    }

}
