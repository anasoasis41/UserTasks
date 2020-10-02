package com.riahi.usertasks.ui.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.riahi.usertasks.R
import com.riahi.usertasks.databinding.FragmentUserTasksBinding


class UserTasksFragment : Fragment() {

    private lateinit var binding: FragmentUserTasksBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::binding.isInitialized) {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host)
            binding = FragmentUserTasksBinding.inflate(inflater, container, false)

            binding.toolbarBack.setOnClickListener {
                navController.navigateUp()
            }
        }
        return binding.root


    }

}