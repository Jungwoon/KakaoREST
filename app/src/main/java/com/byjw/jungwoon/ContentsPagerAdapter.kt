package com.byjw.jungwoon

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.byjw.jungwoon.searchPage.SearchFragment
import com.byjw.jungwoon.favoritePage.FavoriteFragment

class ContentsPagerAdapter(
    fragmentManager: FragmentManager,
    private val numberOfTabs: Int
) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> SearchFragment()
            1 -> FavoriteFragment()
            else -> null
        }!!
    }

    override fun getCount() = numberOfTabs

}