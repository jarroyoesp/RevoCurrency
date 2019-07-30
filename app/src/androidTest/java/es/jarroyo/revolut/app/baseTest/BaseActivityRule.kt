package es.jarroyo.revolut.app.baseTest

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import es.jarroyo.revolut.data.source.cache.CacheDataSource
import es.jarroyo.revolut.data.source.disk.DiskDataSource

class BaseActivityRule<A : Activity>(activityClass: Class<A>, initialTouchMode: Boolean, launchActivity: Boolean) : ActivityTestRule<A>(activityClass, initialTouchMode, launchActivity) {

    override fun getActivityIntent(): Intent {
        // add some custom extras and stuff
        return Intent()
    }

    override fun afterActivityFinished() {
        // super.afterActivityFinshed()
        // Clean up mocks
        DiskDataSource(getContext()).deleteAllTables()
        CacheDataSource.clearCache()
    }

    fun launchActivity() {
        this.launchActivity(activityIntent)
    }

    private fun getContext(): Context {
        return InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
    }
}