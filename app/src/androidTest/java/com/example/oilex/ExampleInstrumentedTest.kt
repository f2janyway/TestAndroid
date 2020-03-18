package com.example.oilex
//import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.action.ViewActions.*
//import androidx.test.espresso.assertion.ViewAssertions.matches
//import androidx.test.espresso.matcher.ViewMatchers.withId
//import androidx.test.espresso.matcher.ViewMatchers.withText
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.filters.LargeTest
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//import androidx.test.rule.ActivityTestRule
//
//@RunWith(AndroidJUnit4::class)
//@LargeTest
//class ChangeTextBehaviorTest {
//
//    private lateinit var stringToBetyped: String
//
//    @get:Rule
//    var activityRule: ActivityTestRule<TestActivity>
//            = ActivityTestRule(TestActivity::class.java)
//
//    @Before
//    fun initValidString() {
//        // Specify a valid string.
//        stringToBetyped = "Espresso"
//    }
//
//    @Test
//    fun changeText_sameActivity() {
//        // Type text and then press the button.
//        onView(withId(R.id.edittext1))
//            .perform(typeText(stringToBetyped), closeSoftKeyboard())
//        onView(withId(R.id.button1)).perform(click())
//
//        // Check that the text was changed.
//        onView(withId(R.id.textview1))
//            .check(matches(withText(stringToBetyped)))
//    }
//}


import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyAbove
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import kotlinx.android.synthetic.main.item.view.*
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
//Given - When - Then
/**
Given : Setup the Objects and the state of the app that you need for your test. For this test, what is "given" is that you have a list of tasks where the task is active

When : Do the actual action on the object you`re testing. For this test, it means calling function()

Then : this is where you actually check what happens when you do the action where you check if the test passed or failed

Test Names
Test names are meant to be descriptive.
`subjectUnderTest_actionOrInput_resultState`
* Subject under test is the method or class that is being tested
* Next is the action or input
* Finally you have the expected result
*/
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.example.oilex", appContext.packageName)
//    }
//    @get:Rule val activity = ActivityScenario.launch(TestActivity::class.java)

    // 둘다 가능 (무슨 차이 인지는 모르겠다. 나중에 알아봐)
//    @get:Rule val activity= ActivityTestRule<TestActivity>(TestActivity::class.java)
    @get:Rule val ac = ActivityScenarioRule<TestActivity>(TestActivity::class.java)

    @Test
    fun clickButtonShowTextTest() {
        // Given
        val edittest = "hello"
//        val activity = ActivityScenario.launch(TestActivity::class.java)
        //When
        // withId(R.id.my_view) is a ViewMatcher
        // click() is a ViewAction
        // matches(isDisplayed()) is a ViewAssertion

        onView(withId(R.id.edittext1)).perform(typeText(edittest))
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.textview1)).check(matches(withText(edittest)))

        onView(withId(R.id.edittext1)).perform(clearText(), typeText("good"))
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.textview1)).check(matches(anyOf(withText("good"))))
        onView(withId(R.id.textview2)).check(matches(anyOf(withText("good"))))

        onView(withId(R.id.edittext1)).check(isCompletelyAbove(withId(R.id.textview1)))

//        onView(withId(R.id.button2)).perform(click())

//        onView(withId(R.id.button1_2)).perform(click())

//        onView(withId(R.id.textview1)).perform(pressBack())

        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.recyclerview1)).perform(
            RecyclerViewActions.scrollToPosition<MyAdapter.ViewHolder>(20)
        )

        onView(withId(R.id.recyclerview1)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MyAdapter.ViewHolder>(21,MyViewAction.clickChildViewWithId(R.id.checkbox))
        )

        onView(withId(R.id.recyclerview1)).check(matches(atPosition(20, not(isChecked()))))
    }
}

fun atPosition(position: Int, @NonNull itemMatcher: Matcher<View?>): Matcher<View?>? {
    checkNotNull(itemMatcher)
    return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(view: RecyclerView): Boolean {
            val viewHolder =
                view.findViewHolderForAdapterPosition(position)
                    ?: // has no item on such position
                    return false
            return itemMatcher.matches(viewHolder.itemView.checkbox)
        }
    }
}
object MyViewAction {
    fun clickChildViewWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController?, view: View) {
                val v: View = view.findViewById(id)
                v.performClick()
            }
        }
    }
}