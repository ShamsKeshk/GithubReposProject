package com.example.githuprepo.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githuprepo.databinding.RepoListItemBinding
import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel

class RepositoriesAdapter: ListAdapter<GitHupRepositoryModel,RepositoriesAdapter.ViewHolder>(DIFF_UTIL) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RepoListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{

        private val DIFF_UTIL = object : DiffUtil.ItemCallback<GitHupRepositoryModel>(){
            override fun areItemsTheSame(oldItem: GitHupRepositoryModel, newItem: GitHupRepositoryModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GitHupRepositoryModel, newItem: GitHupRepositoryModel): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }


    inner class ViewHolder(private val repoListItemBinding: RepoListItemBinding):
        RecyclerView.ViewHolder(repoListItemBinding.root){

        fun bind(gitHupRepositoryModel: GitHupRepositoryModel){
            repoListItemBinding.repository = gitHupRepositoryModel
        }
    }


}