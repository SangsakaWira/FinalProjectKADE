package com.example.sangsakawira.matchsub2

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.sangsakawira.matchsub2.R.id.*
import com.example.sangsakawira.matchsub2.UI.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testNextMatchesBehaviour() {
        Espresso.onView(ViewMatchers.withId(navigation))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(navigation))
            .check(matches(isDisplayed()))
        onView(withId(navigation_home)).perform(click())
        Thread.sleep(3000)
        onView(withId(rv_matches)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        Thread.sleep(3000)
        onView(withId(rv_matches)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        pressBack()

    }
    @Test
    fun testLastMatchesBehaviours() {
        Espresso.onView(ViewMatchers.withId(navigation))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(navigation))
            .check(matches(isDisplayed()))
        onView(withId(navigation_dashboard)).perform(click())
        Thread.sleep(3000)
        onView(withId(rv_matches)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        Thread.sleep(3000)
        onView(withId(rv_matches)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        pressBack()
    }

}