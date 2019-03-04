package br.com.andrecouto.alodjinha.ui.activity.home

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import br.com.andrecouto.alodjinha.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun homeActivityTest() {

        val viewGroup = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.drawer_layout),
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0)),
                        0),
                        isDisplayed()))
        viewGroup.check(matches(isDisplayed()))

        val viewGroup2 = onView(
                allOf(withId(R.id.include_toolbar),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drawer_layout),
                                        0),
                                0),
                        isDisplayed()))
        viewGroup2.check(matches(isDisplayed()))

        val linearLayout3 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.include_toolbar),
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                                        0)),
                        0),
                        isDisplayed()))
        linearLayout3.check(matches(isDisplayed()))

        val viewGroup3 = onView(
                allOf(withId(R.id.toolbar),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.include_toolbar),
                                        0),
                                0),
                        isDisplayed()))
        viewGroup3.check(matches(isDisplayed()))

        val viewGroup4 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.toolbar),
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                                        0)),
                        1),
                        isDisplayed()))
        viewGroup4.check(matches(isDisplayed()))

        val frameLayout4 = onView(
                allOf(withId(R.id.frame_home_container),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drawer_layout),
                                        0),
                                1),
                        isDisplayed()))
        frameLayout4.check(matches(isDisplayed()))

        val viewGroup5 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.frame_home_container),
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                                        1)),
                        0),
                        isDisplayed()))
        viewGroup5.check(matches(isDisplayed()))

        val viewGroup6 = onView(
                allOf(withId(R.id.constraintViewPager),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame_home_container),
                                        0),
                                0),
                        isDisplayed()))
        viewGroup6.check(matches(isDisplayed()))

        val viewPager = onView(
                allOf(withId(R.id.bannerSlider),
                        childAtPosition(
                                allOf(withId(R.id.constraintViewPager),
                                        childAtPosition(
                                                IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                                                0)),
                                0),
                        isDisplayed()))
        viewPager.check(matches(isDisplayed()))

        val viewGroup7 = onView(
                allOf(withId(R.id.layout),
                        withParent(allOf(withId(R.id.bannerSlider),
                                childAtPosition(
                                        withId(R.id.constraintViewPager),
                                        0))),
                        isDisplayed()))
        viewGroup7.check(matches(isDisplayed()))

        val imageView2 = onView(
                allOf(withId(R.id.imageViewBanner),
                        childAtPosition(
                                allOf(withId(R.id.layout),
                                        withParent(withId(R.id.bannerSlider))),
                                0),
                        isDisplayed()))
        imageView2.check(matches(isDisplayed()))

        val view = onView(
                allOf(withId(R.id.indicator),
                        childAtPosition(
                                allOf(withId(R.id.constraintViewPager),
                                        childAtPosition(
                                                IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                                                0)),
                                1),
                        isDisplayed()))
        view.check(matches(isDisplayed()))

        val textView2 = onView(
                allOf(withId(R.id.txtCategoryTitle), withText("Categorias"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame_home_container),
                                        0),
                                1),
                        isDisplayed()))
        textView2.check(matches(withText("Categorias")))

        val view2 = onView(
                allOf(withId(R.id.divider_category),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame_home_container),
                                        0),
                                2),
                        isDisplayed()))
        view2.check(matches(isDisplayed()))

        val recyclerView = onView(
                allOf(withId(R.id.recyclerCategories),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame_home_container),
                                        0),
                                3),
                        isDisplayed()))
        recyclerView.check(matches(isDisplayed()))

        val viewGroup8 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.recyclerCategories),
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                                        3)),
                        0),
                        isDisplayed()))
        viewGroup8.check(matches(isDisplayed()))

        val imageView3 = onView(
                allOf(withId(R.id.imgCategory),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerCategories),
                                        0),
                                0),
                        isDisplayed()))
        imageView3.check(matches(isDisplayed()))

        val textView3 = onView(
                allOf(withId(R.id.txtCategory), withText("Games"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerCategories),
                                        0),
                                1),
                        isDisplayed()))
        textView3.check(matches(withText("Games")))

        val viewGroup9 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.recyclerCategories),
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                                        3)),
                        1),
                        isDisplayed()))
        viewGroup9.check(matches(isDisplayed()))

        val imageView4 = onView(
                allOf(withId(R.id.imgCategory),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerCategories),
                                        1),
                                0),
                        isDisplayed()))
        imageView4.check(matches(isDisplayed()))

        val textView4 = onView(
                allOf(withId(R.id.txtCategory), withText("Livros"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerCategories),
                                        1),
                                1),
                        isDisplayed()))
        textView4.check(matches(withText("Livros")))

        val viewGroup10 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.recyclerCategories),
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                                        3)),
                        2),
                        isDisplayed()))
        viewGroup10.check(matches(isDisplayed()))

        val imageView5 = onView(
                allOf(withId(R.id.imgCategory),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerCategories),
                                        2),
                                0),
                        isDisplayed()))
        imageView5.check(matches(isDisplayed()))

        val textView5 = onView(
                allOf(withId(R.id.txtCategory), withText("Celulares"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerCategories),
                                        2),
                                1),
                        isDisplayed()))
        textView5.check(matches(withText("Celulares")))

        val viewGroup11 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.recyclerCategories),
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                                        3)),
                        3),
                        isDisplayed()))
        viewGroup11.check(matches(isDisplayed()))

        val imageView6 = onView(
                allOf(withId(R.id.imgCategory),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerCategories),
                                        3),
                                0),
                        isDisplayed()))
        imageView6.check(matches(isDisplayed()))

        val textView6 = onView(
                allOf(withId(R.id.txtCategory), withText("Informática"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerCategories),
                                        3),
                                1),
                        isDisplayed()))
        textView6.check(matches(withText("Informática")))

        val imageView7 = onView(
                allOf(withId(R.id.productImage),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerProducts),
                                        0),
                                0),
                        isDisplayed()))
        imageView7.check(matches(isDisplayed()))

        val textView8 = onView(
                allOf(withId(R.id.txtProductDescription), withText("Mussum Ipsum, cacilds vidis litro abertis. Atirei o pau no gatis, per gatis num morreus. Não sou faixa preta cumpadi, sou preto inteiris, inteiris. Praesent malesuada urna nisi, quis volutpat erat hendrerit non. Nam vulputate dapibus. Diuretics paradis num copo é motivis de denguis.<br/><br/>Copo furadis é disculpa de bebadis, arcu quam euismod magna. Casamentiss faiz malandris se pirulitá. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum. in elementis mé pra quem é amistosis quis leo.<br/><br/>A ordem dos tratores não altera o pão duris Delegadis gente finis, bibendum egestas augue arcu ut est. Mé faiz elementum girarzis, nisi eros vermeio. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis!"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerProducts),
                                        0),
                                1),
                        isDisplayed()))
        textView8.check(matches(withText("Mussum Ipsum, cacilds vidis litro abertis. Atirei o pau no gatis, per gatis num morreus. Não sou faixa preta cumpadi, sou preto inteiris, inteiris. Praesent malesuada urna nisi, quis volutpat erat hendrerit non. Nam vulputate dapibus. Diuretics paradis num copo é motivis de denguis.<br/><br/>Copo furadis é disculpa de bebadis, arcu quam euismod magna. Casamentiss faiz malandris se pirulitá. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum. in elementis mé pra quem é amistosis quis leo.<br/><br/>A ordem dos tratores não altera o pão duris Delegadis gente finis, bibendum egestas augue arcu ut est. Mé faiz elementum girarzis, nisi eros vermeio. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis!")))

        val view4 = onView(
                allOf(withId(android.R.id.navigationBarBackground),
                        childAtPosition(
                                IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                                1),
                        isDisplayed()))
        view4.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
