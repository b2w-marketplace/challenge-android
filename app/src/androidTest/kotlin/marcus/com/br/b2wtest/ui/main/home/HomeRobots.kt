package marcus.com.br.b2wtest.ui.main.home

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import marcus.com.br.b2wtest.R
import marcus.com.br.b2wtest.ui.main.MainActivity
import marcus.com.br.b2wtest.util.RecyclerViewItemCountAssertion
import marcus.com.br.b2wtest.util.TestHelper
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

class HomeRobots(private var rule: ActivityTestRule<MainActivity>, private val server: MockWebServer) {

    fun initActivity(intent: Intent) {
        rule.launchActivity(intent)
    }

    fun mockResponse() {
        server.setDispatcher(RequestDispatcher())
    }

    fun validateBanner(total: Int) {
        Espresso.onView(ViewMatchers.withId(R.id.fragmentHomeBanners)).check(RecyclerViewItemCountAssertion(total))
    }

    fun validateCategory(total: Int) {
        Espresso.onView(ViewMatchers.withId(R.id.fragmentHomeCategories)).check(RecyclerViewItemCountAssertion(total))
    }

    fun validateBestSeller(total: Int) {
        Espresso.onView(ViewMatchers.withId(R.id.fragmentHomeProducts)).check(RecyclerViewItemCountAssertion(total))
    }

    class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest?): MockResponse {
            if (request?.path.equals("/banner")) {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        TestHelper.getStringFromFile(
                            InstrumentationRegistry.getInstrumentation().context,
                            "get_banner.json"
                        )
                    )
            } else if (request?.path.equals("/categoria")) {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        TestHelper.getStringFromFile(
                            InstrumentationRegistry.getInstrumentation().context,
                            "get_category.json"
                        )
                    )
            } else {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        TestHelper.getStringFromFile(
                            InstrumentationRegistry.getInstrumentation().context,
                            "get_best_sellers.json"
                        )
                    )
            }
        }
    }
}