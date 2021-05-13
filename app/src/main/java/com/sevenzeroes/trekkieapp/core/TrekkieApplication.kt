package com.sevenzeroes.trekkieapp.core

import android.app.Application
import com.sevenzeroes.trekkieapp.core.domain.framework.Interactors
import com.sevenzeroes.trekkieapp.core.domain.interactors.*
import com.sevenzeroes.trekkieapp.core.domain.repository.RemoteDataSourceImpl
import com.sevenzeroes.trekkieapp.core.domain.repository.EpisodesRepository
import com.sevenzeroes.trekkieapp.core.domain.repository.LocalDataSourceImpl

class TrekkieApplication : Application() {

    lateinit var interactors: Interactors

    companion object{
        var instance: TrekkieApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        val episodesRepository = EpisodesRepository(RemoteDataSourceImpl(), LocalDataSourceImpl())

        interactors = Interactors(
            GetSummaries(episodesRepository),
            GetEpisodeSpecifics(episodesRepository),
            SearchEpisodes(episodesRepository),
            SearchEpisodesInDb(episodesRepository),
            GetAllEpisodesFromDb(episodesRepository),
            Insert(episodesRepository)
        )
    }
}