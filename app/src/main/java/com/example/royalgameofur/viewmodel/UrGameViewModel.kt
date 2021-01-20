package com.example.royalgameofur.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.royalgameofur.model.Player
import com.example.royalgameofur.repository.UrGameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UrGameViewModel (application: Application) : AndroidViewModel(application) {

    private val gameRepository = UrGameRepository(application.applicationContext)

//    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun getPlayer1():LiveData<Player>{
        return gameRepository.getPlayer(1)
    }

    fun getPlayer2():LiveData<Player>{
        return gameRepository.getPlayer(2)
    }



}