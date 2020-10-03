package com.riahi.usertasks.ui.tasks

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.riahi.usertasks.R
import com.riahi.usertasks.data.viewmodels.TasksViewModel
import com.riahi.usertasks.data.viewmodels.TasksViewModelFactory
import com.riahi.usertasks.data.viewmodels.UserViewModel
import com.riahi.usertasks.databinding.FragmentUserTasksBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber


class UserTasksFragment : Fragment() {

    private lateinit var binding: FragmentUserTasksBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: TasksViewModel
    private lateinit var viewModelFactory: TasksViewModelFactory
    private var userId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::binding.isInitialized) {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host)
            binding = FragmentUserTasksBinding.inflate(inflater, container, false)

            arguments?.getInt("userId").let {
                if (it != null) {
                    userId = it
                }
            }
            //val app = Application()
            viewModelFactory = TasksViewModelFactory(requireContext(), userId)
            viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(TasksViewModel::class.java)


            binding.toolbarBack.setOnClickListener {
                navController.navigateUp()
            }


            Timber.i("userId $userId")
            listOfTasks()
        }
        return binding.root


    }

    private fun listOfTasks() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.tasksRepository.getTasks(userId)
        }

        viewModel.tasksListData.observe(viewLifecycleOwner, Observer { tasksList ->
            Timber.i("tasksList ${tasksList}")
        })
    }
}