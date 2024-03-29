package es.jarroyo.revolut.ui.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import es.jarroyo.revolut.app.di.component.ApplicationComponent
import es.jarroyo.revolut.app.navigator.Navigator
import es.jarroyo.revolut.ui.App
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    abstract var layoutId: Int

    abstract fun setupInjection(applicationComponent: ApplicationComponent)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInjection(App.graph)
        initView()
    }

    override fun onResume() {
        super.onResume()
        navigator.currentActivity = this
    }

    private fun initView() {
        setContentView(layoutInflater.inflate(layoutId, null))
        ButterKnife.bind(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        navigator.currentActivity = null
        super.onPause()
    }
}