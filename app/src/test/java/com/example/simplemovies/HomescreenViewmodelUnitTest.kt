package com.example.simplemovies


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.homescreen.HomescreenViewModel
import com.example.simplemovies.mockdata.MockedService
import com.example.simplemovies.mockdata.getOrAwait
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.network.Resource
import com.example.simplemovies.repositories.MovieRepository
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class HomescreenViewmodelUnitTest {

    //Enable livedata handling
    @Rule
    @JvmField
    val instantEx = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var viewmodel: HomescreenViewModel

    @Before
    fun initDependencies() {
        viewmodel = HomescreenViewModel(movieRepository)
    }

    @Test
    fun expect_fetchMovies_to_have_items_and_success() {
        //Mock repository
        `when`(movieRepository.getMovies()).thenReturn(MockedService.getMovies())

        //Call movies
        val res = viewmodel.fetchMovies().getOrAwait()

        viewmodel.manageState(res)

        //Null check
        assertNotNull(res.data)
        assertNotNull(viewmodel.displayableMovies.getOrAwait())
        assertEquals(viewmodel.apiStatus.getOrAwait(), APIStatus.DONE)
    }

    @Test
    fun expect_fetchMovies_to_display_error_state() {
        //Mock setup with failed call
        `when`(movieRepository.getMovies()).thenReturn(MockedService.getFailedMovies())

        //Call movies
        val res = viewmodel.fetchMovies().getOrAwait()

        //Manage state
        viewmodel.manageState(res)

        //Check if data is null and apiStatus is in error state
        //property displayableMovies is not checkable since the value gets never set
        assertNull(res.data)
        assertEquals(viewmodel.apiStatus.getOrAwait(), APIStatus.ERROR)
    }

}
