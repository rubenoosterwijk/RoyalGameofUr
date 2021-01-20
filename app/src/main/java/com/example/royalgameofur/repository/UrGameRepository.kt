package com.example.royalgameofur.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.royalgameofur.database.GameDatabase
import com.example.royalgameofur.dao.UrGameDao
import com.example.royalgameofur.model.Player


class UrGameRepository(context: Context) {
    private val UrGameDao: UrGameDao


    init {
        val database = GameDatabase.getDatabase(context)
        UrGameDao =  database!!.UrGameDao()
    }


    fun getPlayer(id: Int):LiveData<Player>{
        return UrGameDao?.getPlayer(id)
    }

    suspend fun insertBoard() {
        UrGameDao?.updateBoard()
    }

    suspend fun insertPlayer(Player: Player) {
        UrGameDao?.updatePlayer(Player)
    }


    suspend fun deletePlayers(){
        UrGameDao?.deletePlayers()
    }
}
