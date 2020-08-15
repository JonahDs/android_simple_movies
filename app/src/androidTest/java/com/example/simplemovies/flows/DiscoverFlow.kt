package com.example.simplemovies.flows

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.simplemovies.MainActivity
import com.example.simplemovies.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class DiscoverFlow {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun discoverFlow() {
        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Open navigation drawer"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val navigationMenuItemView = onView(
            allOf(
                withId(R.id.experimental_fragment),
                childAtPosition(
                    allOf(
                        withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.nav_view),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(click())
    }

    @Test
    fun discoverFlowSelectOptions() {
        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Open navigation drawer"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val navigationMenuItemView = onView(
            allOf(
                withId(R.id.experimental_fragment),
                childAtPosition(
                    allOf(
                        withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.nav_view),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(click())

        Thread.sleep(3000)

        val chip = onView(
            allOf(
                withId(R.id.chip_genre), withText("Fantasy"),
                childAtPosition(
                    allOf(
                        withId(R.id.chipgroup_experimental_genres),
                        childAtPosition(
                            withId(R.id.linearlayout_experimental_toolbar),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        chip.perform(click())

        val chip2 = onView(
            allOf(
                withId(R.id.chip_genre), withText("Action"),
                childAtPosition(
                    allOf(
                        withId(R.id.chipgroup_experimental_genres),
                        childAtPosition(
                            withId(R.id.linearlayout_experimental_toolbar),
                            1
                        )
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        chip2.perform(click())

        val appCompatSpinner = onView(
            allOf(
                withId(R.id.spinner_experimental_userscore),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintlayout_experimental_panelcontainer),
                        childAtPosition(
                            withId(R.id.linearlayout_experimental_toolbar),
                            4
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatSpinner.perform(click())

        val appCompatCheckedTextView = Espresso.onData(Matchers.anything())
            .atPosition(5)
        appCompatCheckedTextView.perform(click())

        val materialButton = onView(
            allOf(
                withId(R.id.button_experimental_search), withText("Search"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintlayout_experimental_panelcontainer),
                        childAtPosition(
                            withId(R.id.linearlayout_experimental_toolbar),
                            4
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())
        Thread.sleep(3000)
    }

    @Test
    fun discoverFlowToDetail() {
        Thread.sleep(3000)
        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Open navigation drawer"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val navigationMenuItemView = onView(
            allOf(
                withId(R.id.experimental_fragment),
                childAtPosition(
                    allOf(
                        withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.nav_view),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(click())

        val chip = onView(
            allOf(
                withId(R.id.chip_genre), withText("Fantasy"),
                childAtPosition(
                    allOf(
                        withId(R.id.chipgroup_experimental_genres),
                        childAtPosition(
                            withId(R.id.linearlayout_experimental_toolbar),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        chip.perform(click())

        val appCompatSpinner = onView(
            allOf(
                withId(R.id.spinner_experimental_userscore),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintlayout_experimental_panelcontainer),
                        childAtPosition(
                            withId(R.id.linearlayout_experimental_toolbar),
                            4
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatSpinner.perform(click())

        val appCompatCheckedTextView = Espresso.onData(Matchers.anything())
            .atPosition(4)
        appCompatCheckedTextView.perform(click())

        val materialButton = onView(
            allOf(
                withId(R.id.button_experimental_search), withText("Search"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintlayout_experimental_panelcontainer),
                        childAtPosition(
                            withId(R.id.linearlayout_experimental_toolbar),
                            4
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        Thread.sleep(7000)

        val appCompatImageView = onView(
            allOf(
                withId(R.id.imageview_grid_movieposter),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerview_experimental_movies),
                        childAtPosition(
                            withId(R.id.linearlayout_experimental_advcontainer),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        Thread.sleep(3000)
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>,
        position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent) &&
                    view == parent.getChildAt(position)
            }
        }
    }
}
