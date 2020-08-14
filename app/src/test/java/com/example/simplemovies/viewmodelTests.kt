package com.example.simplemovies


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.simplemovies.cast.CastViewmodel
import com.example.simplemovies.homescreen.HomescreenViewModel
import com.example.simplemovies.mockdata.MockedService
import com.example.simplemovies.mockdata.getOrAwait
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.repositories.MovieRepository
import junit.framework.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


/**
 * All viewmodels follow the same patterns
 * (1, no nav args needed) init {} calls the repository method, inside this method we 'subscribe' to the (simple)networkbounding
 * this method emits 'load' - '(intermediate)' - 'done' with data from API/DB
 * on each emit the properties inside the viewmodel get set.
 *
 * (2, nav args needed) each viewmodel holds some properties that are either null or 'undefined', the fragment calls a method that tries to set these properties
 * the properties will only get set if A. they are untouched or B. they are touched but are different from incoming parameters.
 * ONLY when these properties get set the repository will be called. This is to prevent making API calls when a configuration chance occurs.
 *
 * Since all viewmodels follow mentioned patterns we only need to test one of each.
 */
@RunWith(MockitoJUnitRunner::class)
class viewmodelTests {


    /**
     * Custom made coroutine to tell our viewmodels which one to use
     * */
    @get:Rule
    val coroutineRule = CoroutineRule()

    /**
     * Allow the observation of livedata
     * */
    @get:Rule
    val instantExec: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var viewmodel: HomescreenViewModel
    private lateinit var castViewmodel: CastViewmodel

    /**
     * Viewmodel pattern 1.
     * Let the repository return an instance of simple networkbounding (all database functions got tested inside androidTest so no need for the overhead)
     * Since the networkbounding emits loading, makes the API call and then emits done we wait for a second to give networkbounding some time.
     * */
    @Test
    fun viewmodel_expect_success() {
        coroutineRule.runBlockingTest {
            `when`(movieRepository.getMoviesOfFlow()).thenReturn(MockedService.getMoviesSuccess())
            viewmodel = HomescreenViewModel(movieRepository)
            assertEquals(viewmodel.apiStatus.getOrAwait(), APIStatus.LOADING)
            Thread.sleep(5000)
            assertEquals(viewmodel.apiStatus.getOrAwait(), APIStatus.DONE)
            assertTrue(viewmodel.displayableMovies.getOrAwait().isNotEmpty())
        }
    }

    /**
     * The repository returns an exception, so we expect our status to be loading -> error.
     * */
    @Test
    fun viewmodel_expect_failure() {
        coroutineRule.runBlockingTest {
            `when`(movieRepository.getMoviesOfFlow()).thenReturn(MockedService.getMoviesFailure())
            viewmodel = HomescreenViewModel(movieRepository)
            assertEquals(viewmodel.apiStatus.getOrAwait(), APIStatus.LOADING)
            Thread.sleep(1000)
            assertEquals(viewmodel.apiStatus.getOrAwait(), APIStatus.ERROR)
        }
    }

    /**
     * Viewmodel pattern 2.
     * Call the method that tries to set the properties, since these are untouched we expect them to get set and the repository call to happen.
     */
    @Test
    fun viewmodel_expect_repocall_to_happen() {
        coroutineRule.runBlockingTest {
            `when`(
                movieRepository.getMovieCast(
                    "type",
                    1
                )
            ).thenReturn(MockedService.getCastSuccess())
            castViewmodel = CastViewmodel(movieRepository)
            castViewmodel.setMovieId(1, "type")
            assertNotNull(castViewmodel.cast.getOrAwait())
        }
    }

    /**
     * Call the method that tries to set the properties, then try to do this again with the same parameters.
     * We expect the repository to only get called once. by fetching the livedata twice and comparing them we know if it got reassigned or not
     * */
    @Test()
    fun viewmodel_expect_repocall_to_notHappen() {
        coroutineRule.runBlockingTest {
            `when`(
                movieRepository.getMovieCast(
                    "type",
                    1
                )
            ).thenReturn(MockedService.getCastSuccess())
            castViewmodel = CastViewmodel(movieRepository)
            castViewmodel.setMovieId(1, "type")
            val data = castViewmodel.cast.getOrAwait()
            assertNotNull(data)

            castViewmodel.setMovieId(1, "type")
            val refetched = castViewmodel.cast.getOrAwait()
            assertEquals(data, refetched)
        }
    }
}
