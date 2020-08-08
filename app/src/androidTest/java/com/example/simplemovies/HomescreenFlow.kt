package com.example.simplemovies


import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.simplemovies.homescreen.PhotoGridAdapter
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomescreenFlow {

    @Rule
    @JvmField
    var rule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    @Test
    fun clickItem() {
        onView(withId(R.id.recyclerview_home_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<PhotoGridAdapter.MovieViewHolder>(
                0,
                RecyclerAction.clickChildViewWithId(R.id.imageview_moviepicker_movieposter)
            )
        )
    }
}

object RecyclerAction {
    fun clickChildViewWithId(id: Int) = object : ViewAction {
        override fun getConstraints(): Matcher<View?>? = null
        override fun getDescription(): String = "Clicked on child with $id"

        override fun perform(uiController: UiController?, view: View?) {
            view?.findViewById<View>(id)?.performClick()
        }
    }

}