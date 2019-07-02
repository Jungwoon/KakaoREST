package com.byjw.jungwoon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout = this.tab_layout
        val viewPager = this.view_pager

        val pagerAdapter = ContentsPagerAdapter(
            fragmentManager = supportFragmentManager,
            numberOfTabs = tabLayout.tabCount
        )
        viewPager.adapter = pagerAdapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) { }

            override fun onTabReselected(tab: TabLayout.Tab) { }
        })

    }
}
