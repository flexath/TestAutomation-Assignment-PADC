package com.zg.burgerjoint.uitests.login

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.zg.burgerjoint.R
import com.zg.burgerjoint.activities.LoginActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
open class LoginSuccessTest {

    companion object {
        const val TEST_USERNAME = "Aung Thiha"
        const val TEST_PASSWORD = "123456"
    }


    @get:Rule
    var activityScenarioRule = activityScenarioRule<LoginActivity>()

//    private val activityTestRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

//    @Before
//    open fun setUp() {
//        activityTestRule.launchActivity(Intent())
//    }

    @Test
    fun checkLoginButtonIsDisplayed() {
        onView(withId(R.id.btnLogin))
            .check(matches(isDisplayed()))
    }

    @Test
    fun enterInformation_navigateToMainScreen() {

        onView(withId(R.id.etUserName))
            .perform(typeText(TEST_USERNAME), closeSoftKeyboard())

        onView(withId(R.id.etPassword))
            .perform(typeText(TEST_PASSWORD), closeSoftKeyboard())

        onView(withId(R.id.btnLogin))
            .perform(click())

        onView(withId(R.id.rvBurgerList))
            .check(matches(isDisplayed()))
    }

}