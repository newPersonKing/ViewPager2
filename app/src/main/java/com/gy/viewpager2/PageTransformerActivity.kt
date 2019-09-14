package com.gy.viewpager2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.integration.testapp.OrientationController
import androidx.viewpager2.integration.testapp.PageTransformerController
import androidx.viewpager2.integration.testapp.cards.CardViewAdapter
import androidx.viewpager2.widget.ViewPager2

/*CompositePageTransformer 组合transform*/
class PageTransformerActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_transformer)

        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = CardViewAdapter()

        OrientationController(viewPager, findViewById(R.id.orientation_spinner)).setUp()
        PageTransformerController(viewPager, findViewById(R.id.transformer_spinner)).setUp()
    }

}