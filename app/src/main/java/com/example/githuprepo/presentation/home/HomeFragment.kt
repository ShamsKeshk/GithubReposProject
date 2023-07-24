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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun observeOnGitHupRepositories(){
        viewModel.repositoriesLiveData().observe(viewLifecycleOwner, Observer {
            if (it.isLoading()){
                Toast.makeText(requireContext(),"Loading", Toast.LENGTH_LONG).show()
            }else if (it.isSuccessful()){
                Toast.makeText(requireContext(),"Successful", Toast.LENGTH_LONG).show()
                it.getData()?.forEach {
                    Toast.makeText(requireContext(),"Data ${it.name}", Toast.LENGTH_LONG).show()
                }
            }else if (it.isFailed()){
                Toast.makeText(requireContext(),"Data ${it.getError()?.message}", Toast.LENGTH_LONG).show()
            }
        })

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