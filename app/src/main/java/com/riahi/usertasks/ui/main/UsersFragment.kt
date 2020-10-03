package com.riahi.usertasks.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.riahi.usertasks.R
import com.riahi.usertasks.data.viewmodels.UserViewModel
import com.riahi.usertasks.databinding.FragmentUsersBinding
import timber.log.Timber
import kotlin.math.absoluteValue

class UsersFragment : Fragment(), UserItemClickListener {

    private lateinit var binding: FragmentUsersBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::binding.isInitialized) {
            binding = FragmentUsersBinding.inflate(inflater, container, false)
            viewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host)

            listOfUsers()
        }
        return binding.root
    }

    private fun listOfUsers() {
        viewModel.usersListData.observe(viewLifecycleOwner, Observer { usersList ->
            val adapter = UsersRecyclerAdapter(usersList, this)
            binding.userAdapter = adapter
        })
    }

    override fun onUserClicked(id: Int) {
        val bundle = bundleOf("userId" to id)
        navController.navigate(R.id.action_usersFragment_to_userTasksFragment, bundle)

    }

}