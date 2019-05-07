package br.com.amedigital.product

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import br.com.amedigital.R
import br.com.amedigital.model.Category
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
open  class ProductByCategoryActivityScreen {
    @Rule
    @JvmField
    val mActivityRule: ActivityTestRule<ProductByCategoryActivity> =
        object : ActivityTestRule<ProductByCategoryActivity>(ProductByCategoryActivity::class.java) {
            override fun getActivityIntent(): Intent {

                val category = Category()
                with(category) {
                    id = 1
                    description = "Games"
                    urlImage = "http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png"
                }

                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                val result = Intent(targetContext, ProductByCategoryActivity::class.java)
                result.putExtra("category", category)
                return result
            }
        }

    @Test
    fun showTitleActionBar() {
        onView(allOf(instanceOf(TextView::class.java), withParent(withResourceName("action_bar"))))
            .check(matches(withText("games")))
    }

    @Test
    fun clickOnItemRecyclerCategory() {
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.progressBarProdCategory))
            .check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewProductsCategory))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Thread.sleep(2000)
    }

}