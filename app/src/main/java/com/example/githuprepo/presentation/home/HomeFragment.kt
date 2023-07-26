package com.example.githuprepo.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.githuprepo.R
import com.example.githuprepo.databinding.FragmentHomeBinding
import com.example.githuprepo.databinding.RepoListItemBinding
import com.example.githuprepo.presentation.model.getData
import com.example.githuprepo.presentation.model.getError
import com.example.githuprepo.presentation.viewmodel.RepositoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: RepositoriesViewModel by activityViewModels()

    private lateinit var binding: FragmentHomeBinding

    private lateinit var repositoriesAdapter: RepositoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        repositoriesAdapter = RepositoriesAdapter()

        binding.rvRepositories.adapter = repositoriesAdapter

        observeOnGitHupRepositories()

        listenToSwipeToRefreshToForceLoad()

        return binding.root
    }

    private fun listenToSwipeToRefreshToForceLoad(){
        binding.swipeToRefreshRepositories.setOnRefreshListener {
            viewModel.syncRepositories(true)

            binding.swipeToRefreshRepositories.isRefreshing = false
        }
    }

    private fun observeOnGitHupRepositories(){
        viewModel.repositoriesLiveData().observe(viewLifecycleOwner) { result ->
            if (result == null)
                return@observe

            binding.result = result

            if (result.isLoading()) {
                binding.shimmerViewContainer.shimmerViewContainer.startShimmer()
            } else if (result.isSuccessful()) {
                binding.shimmerViewContainer.shimmerViewContainer.stopShimmer()
                repositoriesAdapter.submitList(result.getData())
            } else if (result.isFailed()) {
                binding.shimmerViewContainer.shimmerViewContainer.stopShimmer()
            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment HomeFragment.
         */
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}