package com.gy.viewpager2

import android.os.Bundle
import androidx.viewpager2.integration.testapp.cards.Card
import com.google.android.material.tabs.TabLayout

class CardViewTabLayoutActivity :CardViewActivity(){

    private lateinit var tabLayout: TabLayout

    override val layoutId: Int = R.layout.activity_tablayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            tab.text = Card.DECK[position].toString()
        }.attach()
    }

}