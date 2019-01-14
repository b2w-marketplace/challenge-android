package b2w.com.br.olodjinha.home;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import b2w.com.br.olodjinha.MainActivity;
import b2w.com.br.olodjinha.R;
import b2w.com.br.olodjinha.injection.MainComponent;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class HomeFragmentRobot {

    private ActivityTestRule<MainActivity> mRule;

    HomeFragmentRobot(ActivityTestRule<MainActivity> rule) {
        mRule = rule;
    }

    HomeFragmentRobot launch() {
        Intent i = new Intent();
        mRule.launchActivity(i);
        return this;
    }

    HomeFragmentRobot verifyScreenElementes() {
        onView(withId(R.id.layout_banners)).check(matches(isDisplayed()));
        onView(withId(R.id.layout_categories)).check(matches(isDisplayed()));
        onView(withId(R.id.layout_best_sellers)).check(matches(isDisplayed()));
        return this;
    }
}
