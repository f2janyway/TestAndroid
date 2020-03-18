package com.example.oilex

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.PositionAssertions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Test names are meant to be descriptive.
 * subjectUnderTest_actionOrInput_resultState`
 * Subject under test is the method or class that is being tested
 * Next is the action or input
 * Finally you have the expected result
 * */
@RunWith(AndroidJUnit4::class)
@LargeTest
class TestActivityUITest {

    // 둘다 가능 (무슨 차이 인지는 모르겠다. 나중에 알아봐)
    //  @get:Rule val activity= ActivityTestRule<TestActivity>(TestActivity::class.java)
    @get:Rule
    val testActivity = ActivityScenarioRule<TestActivity>(TestActivity::class.java)


    @Test
    fun recyclerView_scrollButtonClick_Checked(){
        onView(withId(R.id.button3)).perform(ViewActions.click())
        onView(withId(R.id.recyclerview1)).perform(
            RecyclerViewActions.scrollToPosition<MyAdapter.ViewHolder>(20)
        )

        onView(withId(R.id.recyclerview1)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MyAdapter.ViewHolder>(21,MyViewAction.clickChildViewWithId(R.id.checkbox))
        )

        onView(withId(R.id.recyclerview1)).check(
            ViewAssertions.matches(
                atPosition(
                    21,
//                    CoreMatchers.not(ViewMatchers.isChecked())
                isChecked()
                )
            )
        )
    }
    @Test
    fun testActivity_editButtonClick_changed() {
        val edittest = "good morning"

        onView(withId(R.id.edittext1)).perform(typeText(edittest))
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.textview1))
            .check(ViewAssertions.matches(ViewMatchers.withText(edittest)))

        onView(withId(R.id.edittext1))
            .perform(ViewActions.clearText(), typeText("good"))
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.textview1))
            .check(ViewAssertions.matches(CoreMatchers.anyOf(ViewMatchers.withText("good"))))
        onView(withId(R.id.textview2))
            .check(ViewAssertions.matches(CoreMatchers.anyOf(ViewMatchers.withText("good"))))

        onView(withId(R.id.edittext1))
            .check(PositionAssertions.isCompletelyAbove(withId(R.id.textview1)))
    }

    @Test
    fun test_RecyclerData_click(){
        //move activity
        onView(withId(R.id.button2)).perform(click())

    }
}
