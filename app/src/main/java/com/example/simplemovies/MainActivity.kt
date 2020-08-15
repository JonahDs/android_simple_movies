package com.example.simplemovies

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.example.simplemovies.databinding.ActivityMainBinding
import com.example.simplemovies.homescreen.HomescreenFragmentDirections

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        drawerLayout = binding.drawerLayout

        val navController = findNavController(R.id.navhost_fragment)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)


        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)


        //If the user wants to search this code will trigger
        //every navigation will happen from homescreen so the backstack will always return to homescreen
        //
        //if intent.action is not set to null after a search this code will trigger again if the user rotates
        //the screen on any other fragment
        if (Intent.ACTION_SEARCH === intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also {
                findNavController(R.id.navhost_fragment).navigate(
                    HomescreenFragmentDirections.actionMoviesToSearchLanding(
                        it
                    )
                )
            }
            intent.action = null
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navhost_fragment).navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /**
     * Setup the search
     *
     * @param menu Menu
     * @return true to append search to the appbar
     * */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        //Setup the search functionality
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            queryHint = "Search movies by title"
        }
        return true
    }
}
