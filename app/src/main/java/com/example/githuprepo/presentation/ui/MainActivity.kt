package com.example.githuprepo.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.githuprepo.R
import com.example.githuprepo.presentation.model.getData
import com.example.githuprepo.presentation.model.getError
import com.example.githuprepo.presentation.viewmodel.RepositoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: RepositoriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel.repositoriesLiveData().observe(this, Observer {
            if (it.isLoading()){
                Toast.makeText(this,"Loading",Toast.LENGTH_LONG).show()
            }else if (it.isSuccessful()){
                Toast.makeText(this,"Successful",Toast.LENGTH_LONG).show()
                it.getData()?.forEach {
                    Toast.makeText(this,"Data ${it.name}",Toast.LENGTH_LONG).show()
                }
            }else if (it.isFailed()){
                Toast.makeText(this,"Data ${it.getError()?.message}",Toast.LENGTH_LONG).show()
            }
        })

    }
}