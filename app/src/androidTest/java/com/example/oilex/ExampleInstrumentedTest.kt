package com.example.oilex

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.PositionAssertions
import androidx.test.espresso.assertion.PositionAssertions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.oilex", appContext.packageName)
    }

    @Test
    fun clickButtonShowTextTest() {
        // Given
        val edittest = "hello"
        val activity = ActivityScenario.launch(TestActivity::class.java)

        //When
        // withId(R.id.my_view) is a ViewMatcher
        // click() is a ViewAction
        // matches(isDisplayed()) is a ViewAssertion

        onView(withId(R.id.edittext1)).perform(typeText(edittest))
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.textview1)).check(matches(withText(edittest)))

        onView(withId(R.id.edittext1)).perform(clearText(),typeText("good"))
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.textview1)).check(matches(anyOf(withText("good"))))
        onView(withId(R.id.textview2)).check(matches(anyOf(withText("good"))))

        onView(withId(R.id.edittext1)).check(isCompletelyAbove(withId(R.id.textview1)))

        onView(withId(R.id.button2)).perform(click())

//        onView(withId(R.id.button1_2)).perform(click())

        onView(withId(R.id.textview1_2)).perform(pressBack())

    }
}

