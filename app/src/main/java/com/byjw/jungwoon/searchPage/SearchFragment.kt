package com.byjw.jungwoon.searchPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.byjw.jungwoon.R
import com.byjw.jungwoon.util.otto.BusProvider
import com.byjw.jungwoon.util.otto.event.BusEventSearchKeyword
import com.byjw.jungwoon.util.otto.event.BusEventUnlikeToSearch
import com.byjw.jungwoon.searchPage.model.SearchModel
import com.byjw.jungwoon.searchPage.view.SearchViewAdapter
import com.byjw.jungwoon.searchPage.presenter.SearchPresenter
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment() {

    private lateinit var searchPresenter: SearchPresenter
    lateinit var emptyLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BusProvider.register(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        emptyLayout = view.search_layout_empty

        val searchModel = SearchModel()
        val searchViewAdapter = SearchViewAdapter(fragmentView = view)

        searchPresenter = SearchPresenter(searchModel, searchViewAdapter)

        view.search_recycler_view.setHasFixedSize(true)
        view.search_recycler_view.layoutManager = LinearLayoutManager(context)
        view.search_recycler_view.adapter = searchViewAdapter

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        BusProvider.unregister(this)
    }

    @Subscribe
    fun searchBus(busEventSearchKeyword: BusEventSearchKeyword) {
        searchPresenter.addSearchResponseByKeyword(busEventSearchKeyword.keyword)
    }

    @Subscribe
    fun unlikeBus(busEventUnlikeToSearch: BusEventUnlikeToSearch) {
        searchPresenter.unlike(busEventUnlikeToSearch.document)
    }

}
