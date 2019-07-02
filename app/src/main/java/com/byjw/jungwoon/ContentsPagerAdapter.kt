package com.byjw.jungwoon

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.byjw.jungwoon.page.BaseContract
import com.byjw.jungwoon.page.search.SearchFragment
import com.byjw.jungwoon.page.favorite.FavoriteFragment
import com.byjw.jungwoon.page.favorite.FavoritePresenter
import com.byjw.jungwoon.page.favorite.FavoriteViewAdapter
import com.byjw.jungwoon.page.search.model.SearchModel
import com.byjw.jungwoon.page.search.presenter.SearchPresenter
import com.byjw.jungwoon.page.search.view.SearchViewAdapter

class ContentsPagerAdapter(
    fragmentManager: FragmentManager,
    private val numberOfTabs: Int
) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        val favoritePresenter = FavoritePresenter(view = FavoriteViewAdapter())

        val searchPresenter = SearchPresenter(
            model = SearchModel(),
            view = SearchViewAdapter(favoritePresenter)
        )

        return when(position) {
            0 -> getSearchFragment(searchPresenter)
            1 -> getFavoriteFragment(favoritePresenter)
            else -> null
        }!!
    }

    override fun getCount() = numberOfTabs

    private fun getSearchFragment(presenter: BaseContract.BasePresenter): SearchFragment {
        val searchFragment = SearchFragment()
        searchFragment.arguments = getPresenterBundle(presenter)
        return searchFragment
    }

    private fun getFavoriteFragment(presenter: BaseContract.BasePresenter): FavoriteFragment {
        val favoriteFragment = FavoriteFragment()
        favoriteFragment.arguments = getPresenterBundle(presenter)
        return favoriteFragment
    }

    private fun getPresenterBundle(presenter: BaseContract.BasePresenter): Bundle {
        val bundle = Bundle()
        bundle.putSerializable("presenter", presenter)
        return bundle
    }

}