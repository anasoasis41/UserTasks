package com.riahi.usertasks.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.riahi.usertasks.data.viewmodels.UserViewModel
import com.riahi.usertasks.databinding.FragmentUsersBinding
import timber.log.Timber

class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::binding.isInitialized) {
            binding = FragmentUsersBinding.inflate(inflater, container, false)
            viewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

            listOfUsers()
        }
        return binding.root
    }

    private fun listOfUsers() {
        viewModel.usersListData.observe(viewLifecycleOwner, Observer { usersList ->
            Timber.i("users $usersList")
            val adapter = UsersRecyclerAdapter(usersList, requireContext())
            binding.userAdapter = adapter
        })
    }

}