package com.example.simplemovies.flows


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.simplemovies.MainActivity
import com.example.simplemovies.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class HomescreenFlow {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun homescreenFlow() {
        Thread.sleep(3000)
        val appCompatImageView = onView(
            allOf(
                withId(R.id.imageview_grid_movieposter),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerview_home_movies),
                        childAtPosition(
                            withId(R.id.constraintlayout_home_fragmentcontainer),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())
    }


    @Test
    fun homescreenFlowToCast() {
        Thread.sleep(3000)
        val appCompatImageView = onView(
            allOf(
                withId(R.id.imageview_grid_movieposter),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerview_home_movies),
                        childAtPosition(
                            withId(R.id.constraintlayout_home_fragmentcontainer),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        Thread.sleep(3000)

        val materialButton = onView(
            allOf(
                withId(R.id.button_detail_showcast), withText("Show complete cast and crew"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearlayout_detail_castcontainer),
                        childAtPosition(
                            withId(R.id.constraintlayout_detail_fragmentcontainer),
                            8
                        )
                    ),
                    2
                )
            )
        )
        materialButton.perform(scrollTo(), click())
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
