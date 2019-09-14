package com.gy.viewpager2

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.integration.testapp.OrientationController
import androidx.viewpager2.integration.testapp.UserInputController
import androidx.viewpager2.integration.testapp.cards.Card
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_VERTICAL

/*FragmentActivity 是为了兼容3.0版本 不可以视同fragment 的毛病*/
abstract class BaseCardActivity : AppCompatActivity(){

    protected lateinit var viewpager : ViewPager2;

    private lateinit var cardSelector: Spinner
    private lateinit var smoothScrollCheckBox: CheckBox
    private lateinit var rotateCheckBox: CheckBox
    private lateinit var translateCheckBox: CheckBox
    private lateinit var scaleCheckBox: CheckBox
    private lateinit var gotoPage: Button

    private val translateX get() = viewpager.orientation == ORIENTATION_VERTICAL &&
            translateCheckBox.isChecked
    private val translateY get() = viewpager.orientation == ORIENTATION_HORIZONTAL &&
            translateCheckBox.isChecked

    private val mAnimator = ViewPager2.PageTransformer { page, position ->
        Log.i("cccccccccccccc","position=="+position)
        val absPos = Math.abs(position)
        page.apply {
            rotation = if (rotateCheckBox.isChecked) position * 360 else 0f
            translationY = if (translateY) absPos * 500f else 0f
            translationX = if (translateX) absPos * 350f else 0f
            if (scaleCheckBox.isChecked) {
                val scale = if (absPos > 1) 0F else 1 - absPos
                scaleX = scale
                scaleY = scale
            } else {
                scaleX = 1f
                scaleY = 1f
            }
        }
    }

    protected open val layoutId: Int = R.layout.activity_no_tablayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        viewpager = findViewById(R.id.view_pager)
        cardSelector = findViewById(R.id.card_spinner)
        smoothScrollCheckBox = findViewById(R.id.smooth_scroll_checkbox)
        rotateCheckBox = findViewById(R.id.rotate_checkbox)
        translateCheckBox = findViewById(R.id.translate_checkbox)
        scaleCheckBox = findViewById(R.id.scale_checkbox)
        gotoPage = findViewById(R.id.jump_button)

        UserInputController(viewpager, findViewById(R.id.disable_user_input_checkbox)).setUp()
        OrientationController(viewpager, findViewById(R.id.orientation_spinner)).setUp()
        cardSelector.adapter = createCardAdapter()

        viewpager.setPageTransformer(mAnimator)

        gotoPage.setOnClickListener {
            val card = cardSelector.selectedItemPosition
            val smoothScroll = smoothScrollCheckBox.isChecked
            viewpager.setCurrentItem(card, smoothScroll)
        }

        rotateCheckBox.setOnClickListener { viewpager.requestTransform() }
        translateCheckBox.setOnClickListener { viewpager.requestTransform() }
        scaleCheckBox.setOnClickListener { viewpager.requestTransform() }
    }


    private fun createCardAdapter(): SpinnerAdapter {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Card.DECK)
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)
        return adapter
    }

}