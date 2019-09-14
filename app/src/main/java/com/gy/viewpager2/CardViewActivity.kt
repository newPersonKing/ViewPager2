package com.gy.viewpager2

import android.os.Bundle
import androidx.viewpager2.integration.testapp.cards.CardViewAdapter

open class CardViewActivity : BaseCardActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewpager.adapter = CardViewAdapter()
    }

}