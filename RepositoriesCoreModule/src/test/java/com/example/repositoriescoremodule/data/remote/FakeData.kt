package com.example.repositoriescoremodule.data.remote

import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel

internal class FakeData {

    companion object{

        fun getRemoteData(): List<GitHupRepositoryModel> {
            return listOf(
                GitHupRepositoryModel("Payment Repo",
                "great repo to make payment online",
            "Kotlin",10f),
                GitHupRepositoryModel("Booking Repo",
                    "great repo to Booking any hotel online",
                    "Python",7f))
        }
    }
}