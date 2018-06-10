package br.com.wcisang.alojinha;

import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import br.com.wcisang.alojinha.Mocks.ProductMock;
import br.com.wcisang.alojinha.model.Product;
import br.com.wcisang.alojinha.ui.activity.ProductDetailActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by WCisang on 10/06/2018.
 */
public class ProductDetailActivityTest {

    @Rule
    public ActivityTestRule<ProductDetailActivity>
            mActivityRule = new ActivityTestRule<>(ProductDetailActivity.class, false, false);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearsDown() {
        Intents.release();
    }

    @Test
    public void checkvVsibility() {
        mActivityRule.launchActivity(createIntent());
        onView(withId(R.id.product_name)).check(matches(isDisplayed()));
        onView(withId(R.id.product_precode)).check(matches(isDisplayed()));
        onView(withId(R.id.product_precopor)).check(matches(isDisplayed()));
        onView(withId(R.id.desc)).check(matches(isDisplayed()));
    }

    @Test
    public void checkReservation() {
        mActivityRule.launchActivity(createIntent());
        onView(withId(R.id.fab)).perform(click());
        clickOkReservation();
    }

    private void clickOkReservation() {
        try {
            UiDevice device = UiDevice.getInstance(getInstrumentation());
            UiObject okButton = device.findObject(new UiSelector()
                    .clickable(true)
                    .checkable(false)
                    .index(1));
            if (okButton.exists()) {
                okButton.click();
            }
        } catch (UiObjectNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private Intent createIntent() {
        return new Intent().putExtra(ProductDetailActivity.PRODUCT, getProduct());
    }

    private Product getProduct() {
        return ProductMock.getFullProduct();
    }
}
