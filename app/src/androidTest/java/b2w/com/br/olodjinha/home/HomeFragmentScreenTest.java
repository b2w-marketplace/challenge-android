package b2w.com.br.olodjinha.home;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import b2w.com.br.olodjinha.MainActivity;

@RunWith(AndroidJUnit4.class)
public class HomeFragmentScreenTest {

    @Rule
    public ActivityTestRule<MainActivity>
            mActivityRule = new ActivityTestRule<>(MainActivity.class, false, false);

    HomeFragmentRobot ROBOT = new HomeFragmentRobot(mActivityRule);

    @Test
    public void verifyScreenElements() {
        ROBOT.launch()
                .verifyScreenElementes();
    }

}
