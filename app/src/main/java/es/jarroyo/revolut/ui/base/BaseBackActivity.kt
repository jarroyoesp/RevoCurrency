package es.jarroyo.revolut.ui.base

import android.os.Bundle
import androidx.core.content.ContextCompat
import es.jarroyo.revolut.R
import kotlinx.android.synthetic.main.activity_base_back.*

abstract class BaseBackActivity : BaseActivity() {
    override var layoutId = R.layout.activity_base_back

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configView()
    }

    fun configView(){
        prepareToolbar()
    }


    private fun prepareToolbar() {
        setSupportActionBar(activity_base_back_toolbar)
        showBackButton()
    }

    private fun showBackButton() {
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            supportActionBar!!.setTitle("")
            val upArrow = ContextCompat.getDrawable(this, R.drawable.ic_back_button)
            supportActionBar!!.setHomeAsUpIndicator(upArrow)
        }
    }
}
