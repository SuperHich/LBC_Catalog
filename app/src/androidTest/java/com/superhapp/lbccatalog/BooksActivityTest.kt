package com.superhapp.lbccatalog

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.superhapp.lbccatalog.ui.BooksActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class BooksActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(BooksActivity::class.java)

    @Test
    fun test_isListVisible_onAppLaunch() {
        onView(withId(R.id.recyclerView)).check(ViewAssertions.matches(isDisplayed()))
    }

}