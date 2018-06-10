package br.com.wcisang.alojinha;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import br.com.wcisang.alojinha.Mocks.CategoryMock;
import br.com.wcisang.alojinha.model.Category;
import br.com.wcisang.alojinha.ui.activity.CategoryListActivity;
import br.com.wcisang.alojinha.ui.activity.ProductDetailActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
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
public class CategoryListActivityTest {

    @Rule
    public ActivityTestRule<CategoryListActivity>
            mActivityRule = new ActivityTestRule<>(CategoryListActivity.class, false, false);

    @Before
    public void setUp(){
        Intents.init();
    }

    @After
    public void tearsDown(){
        Intents.release();
    }

    @Test
    public void checkProductListVisibility(){
        mActivityRule.launchActivity(createIntent());
        onView(withId(R.id.recyclerview_product)).check(matches(isDisplayed()));
    }

    @Test
    public void checkProductListClick(){
        mActivityRule.launchActivity(createIntent());
        Matcher<Intent> matcher = allOf(
                hasComponent(ProductDetailActivity.class.getName()),
                hasExtraWithKey(ProductDetailActivity.PRODUCT)
        );

        Instrumentation.ActivityResult
                result = new Instrumentation.ActivityResult(Activity.RESULT_OK, null);

        intending(matcher).respondWith(result);

        onView(withId(R.id.recyclerview_product)).perform(actionOnItemAtPosition(0, click()));

        intended(matcher);
    }

    private Intent createIntent() {
        return new Intent().putExtra(CategoryListActivity.CATEGORY, getCategory());
    }

    private Category getCategory() {
        return CategoryMock.getGamesCategory();
    }

}
