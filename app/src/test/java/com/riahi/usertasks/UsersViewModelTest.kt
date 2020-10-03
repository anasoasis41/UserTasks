package com.riahi.usertasks

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.riahi.usertasks.data.UsersDatabase
import com.riahi.usertasks.data.models.users.Users
import com.riahi.usertasks.data.services.UserService
import com.riahi.usertasks.data.viewmodels.UserViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UsersViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var app: Application

    @Mock
    private lateinit var userService: UserService

    @Mock
    private lateinit var userDatabase: UsersDatabase


    @Before
    fun setUp() {
        // do something if required
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<Users>())
                .`when`(userService)
                .getUserData()
            val viewModel = UserViewModel(app)
            //viewModel.usersListData.observeForever()
            verify(userService).getUserData()
        }
    }
}