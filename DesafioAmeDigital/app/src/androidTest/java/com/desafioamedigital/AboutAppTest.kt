package com.desafioamedigital

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.desafioamedigital.ui.activity.about.AboutActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AboutAppTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(AboutActivity::class.java)

    @Test
    fun menuHomeClick(){
        about {
            clickHomeMenuItem()
        }
    }

}