package com.byjw.jungwoon.page.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.byjw.jungwoon.R
import com.byjw.jungwoon.page.search.view.SearchViewAdapter
import com.byjw.jungwoon.page.search.presenter.SearchPresenter
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment() {

    lateinit var searchPresenter: SearchPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        searchPresenter = arguments?.getSerializable("presenter") as SearchPresenter

        view.search_recycler_view.setHasFixedSize(true)
        view.search_recycler_view.layoutManager = LinearLayoutManager(context)
        view.search_recycler_view.adapter = searchPresenter.view as SearchViewAdapter

        return view
    }

    override fun onResume() {
        super.onResume()

        searchPresenter.addSearchResponseByKeyword("HOT")

    }

}
