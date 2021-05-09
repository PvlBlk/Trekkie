package com.sevenzeroes.trekkieapp

import android.app.Application
import com.sevenzeroes.trekkieapp.core.domain.framework.Interactors
import com.sevenzeroes.trekkieapp.core.domain.framework.RemoteDataSource
import com.sevenzeroes.trekkieapp.core.domain.interactors.GetEpisodeSpecifics
import com.sevenzeroes.trekkieapp.core.domain.interactors.SearchEpisodes
import com.sevenzeroes.trekkieapp.core.domain.repository.EpisodesRepository

class TrekkieApplication : Application() {

    lateinit var interactors: Interactors

    companion object{
        var instance: TrekkieApplication? = null

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        val remoteEpisodesRepository = EpisodesRepository(RemoteDataSource())

        interactors = Interactors(GetEpisodeSpecifics(remoteEpisodesRepository), SearchEpisodes(remoteEpisodesRepository))
    }
}