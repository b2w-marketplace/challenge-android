package br.com.wcisang.alojinha;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.wcisang.alojinha.ui.activity.CategoryListActivity;
import br.com.wcisang.alojinha.ui.activity.MainActivity;
import br.com.wcisang.alojinha.ui.activity.ProductDetailActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by WCisang on 10/06/2018.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity>
            mActivityRule = new ActivityTestRule<>(MainActivity.class, false, true);

    @Before
    public void setUp(){
        Intents.init();
    }

    @After
    public void tearsDown(){
        Intents.release();
    }

    @Test
    public void checkBannerVisibility(){
        onView(withId(R.id.viewPager)).check(matches(isDisplayed()));
    }

    @Test
    public void checkCategoriesVisibility(){
        onView(withId(R.id.recyclerview_category)).check(matches(isDisplayed()));
    }

    @Test
    public void checkBestSellersVisibility(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.recyclerview_product)).check(matches(isDisplayed()));
    }

    @Test
    public void checkBannerSwipe(){
        onView(withId(R.id.viewPager)).perform(swipeLeft());
        onView(withId(R.id.viewPager)).perform(swipeLeft());
        onView(withId(R.id.viewPager)).perform(swipeRight());
        onView(withId(R.id.viewPager)).perform(swipeRight());
    }

    @Test
    public void scrollCategoriesList(){
        onView(withId(R.id.recyclerview_category)).perform(swipeLeft());
        onView(withId(R.id.recyclerview_category)).perform(swipeLeft());
    }

    @Test
    public void checkCategoryListItemClick(){
        mActivityRule.launchActivity(new Intent());
        Matcher<Intent> matcher = allOf(
                hasComponent(CategoryListActivity.class.getName()),
                hasExtraWithKey(CategoryListActivity.CATEGORY)
        );

        Instrumentation.ActivityResult
                result = new Instrumentation.ActivityResult(Activity.RESULT_OK, null);

        intending(matcher).respondWith(result);

        onView(withId(R.id.recyclerview_category)).perform(actionOnItemAtPosition(0, click()));

        intended(matcher);
    }

    @Test
    public void checkBestSellersListItemClick(){
        mActivityRule.launchActivity(new Intent());
        Matcher<Intent> matcher = allOf(
                hasComponent(ProductDetailActivity.class.getName()),
                hasExtraWithKey(ProductDetailActivity.PRODUCT)
        );

        Instrumentation.ActivityResult
                result = new Instrumentation.ActivityResult(Activity.RESULT_OK, null);

        intending(matcher).respondWith(result);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.recyclerview_product)).perform(actionOnItemAtPosition(0, click()));

        intended(matcher);
    }


}
