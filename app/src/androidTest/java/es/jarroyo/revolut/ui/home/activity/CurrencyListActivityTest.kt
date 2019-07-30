package es.jarroyo.revolut.ui.home.activity

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import es.jarroyo.revolut.R
import es.jarroyo.revolut.app.baseTest.BaseActivityRule
import es.jarroyo.revolut.data.factory.TestCurrencyListFactory
import es.jarroyo.revolut.ui.currencyList.activity.CurrencyListActivity
import es.jarroyo.revolut.ui.utils.RecyclerViewMatcher
import es.jarroyo.revolut.utils.TestNetworkSystem
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CurrencyListActivityTest {

    @Rule
    @JvmField
    var mActivityRule = BaseActivityRule(CurrencyListActivity::class.java,
        initialTouchMode = true,
        launchActivity = false
    )


    @Before
    fun insertData() {

    }

    @Test
    fun GIVEN_no_favourite_currency_WHEN_launch_app_THEN_currecy_rv_is_displayed() {
        mActivityRule.launchActivity()
        onView(withId(R.id.fragment_currency_list_rv)).check(matches(isDisplayed()))
    }

    @Test
    fun GIVEN_no_favourite_currency_WHEN_launch_app_and_NO_internet_THEN_show_error() {
        TestNetworkSystem.mIsNetworkAvailable = false
        mActivityRule.launchActivity()

        onView(withId(R.id.fragment_currency_list_layout_no_updated)).check(matches(isDisplayed()))

        TestNetworkSystem.mIsNetworkAvailable = true
    }

    @Test
    fun GIVEN_no_favourite_currency_WHEN_launch_app_THEN_show_EUR_as_favourite_currency() {
        mActivityRule.launchActivity()

        onView(withRecyclerView(R.id.fragment_currency_list_rv).atPosition(0))
            .check(matches(hasDescendant(withText("EUR"))))


        onView(withRecyclerView(R.id.fragment_currency_list_rv).atPosition(1))
            .check(matches(hasDescendant(withText(TestCurrencyListFactory.FIRST_RATE_CURRENCY_NAME))))
    }


    @Test
    fun GIVEN_EUR_favourite_currency_WHEN_click_other_currency_THEN_this_currency_on_top() {
        mActivityRule.launchActivity()

        onView(withRecyclerView(R.id.fragment_currency_list_rv).atPosition(0))
            .check(matches(hasDescendant(withText("EUR"))))


        onView(withRecyclerView(R.id.fragment_currency_list_rv).atPosition(1))
            .check(matches(hasDescendant(withText(TestCurrencyListFactory.FIRST_RATE_CURRENCY_NAME)))).perform(click())

        onView(withRecyclerView(R.id.fragment_currency_list_rv).atPosition(0))
            .check(matches(hasDescendant(withText(TestCurrencyListFactory.FIRST_RATE_CURRENCY_NAME))))
    }

    @Test
    fun GIVEN_favourite_currency_EUR_WHEN_try_to_edit_amount_THEN_pos0_enabled_rest_not_enabled() {
        mActivityRule.launchActivity()

        onView(withRecyclerView(R.id.fragment_currency_list_rv)
            .atPositionOnView(0, R.id.item_rv_currency_et_amount))
            .check(matches(isEnabled()))

        onView(withRecyclerView(R.id.fragment_currency_list_rv)
            .atPositionOnView(1, R.id.item_rv_currency_et_amount))
            .check(matches(not(isEnabled())))
    }

    @Test
    fun GIVEN_favourite_currency_EUR_WHEN_change_amount_to_convert_THEN_updated_rest_of_currencies_with_its_equivalence() {
        mActivityRule.launchActivity()

        onView(withRecyclerView(R.id.fragment_currency_list_rv)
            .atPositionOnView(0, R.id.item_rv_currency_et_amount))
            .perform(replaceText("10"))

        onView(withRecyclerView(R.id.fragment_currency_list_rv)
            .atPositionOnView(1, R.id.item_rv_currency_et_amount))
            .check(matches(withText("16.17")))
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    fun typeSearchViewText(text: String): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                //Ensure that only apply if it is a SearchView and if it is visible.
                return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
            }

            override fun getDescription(): String {
                return "Change view text"
            }

            override fun perform(uiController: UiController, view: View) {
                (view as SearchView).setQuery(text, false)
            }
        }
    }
}