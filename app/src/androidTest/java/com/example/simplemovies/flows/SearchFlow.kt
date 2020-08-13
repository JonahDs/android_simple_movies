package com.example.simplemovies.flows


import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.simplemovies.MainActivity
import com.example.simplemovies.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SearchFlow {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun searchFlow() {
        val appCompatImageView = onView(
            allOf(
                withClassName(`is`("androidx.appcompat.widget.AppCompatImageView")),
                withContentDescription("Search"),
                childAtPosition(
                    allOf(
                        withClassName(`is`("android.widget.LinearLayout")),
                        childAtPosition(
                            withId(R.id.search),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        val searchAutoComplete = onView(
           isAssignableFrom(EditText::class.java)
        )
        searchAutoComplete.perform(replaceText("harry"), pressImeActionButton())

        Thread.sleep(3000)
    }


    @Test
    fun searchFlowToDetail() {
        Thread.sleep(3000)
        val appCompatImageView = onView(
            allOf(
                withClassName(`is`("androidx.appcompat.widget.AppCompatImageView")),
                withContentDescription("Search"),
                childAtPosition(
                    allOf(
                        withClassName(`is`("android.widget.LinearLayout")),
                        childAtPosition(
                            withId(R.id.search),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        val searchAutoComplete = onView(
            isAssignableFrom(EditText::class.java)
        )
        searchAutoComplete.perform(replaceText("harry"))

        Thread.sleep(3000)

        searchAutoComplete.perform(pressImeActionButton())
        val appCompatImageView2 = onView(
            allOf(
                withId(R.id.imageview_grid_movieposter),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerview_search_movies),
                        childAtPosition(
                            withId(R.id.constraintlayout_search_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView2.perform(click())

        Thread.sleep(3000)
    }


    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
