package com.example.royalgameofur.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.royalgameofur.database.GameDatabase
import com.example.royalgameofur.dao.UrGameDao
import com.example.royalgameofur.model.Board
import com.example.royalgameofur.model.Piece
import com.example.royalgameofur.model.Player


class UrGameRepository(context: Context) {
    private val UrGameDao: UrGameDao

    init {
        val database = GameDatabase.getDatabase(context)
        UrGameDao = database!!.UrGameDao()
    }

    suspend fun updatePlayer(Player: Player) {
        UrGameDao?.updatePlayer(Player)
    }

    fun getPlayer(id: Int): LiveData<Player> {
        return UrGameDao?.getPlayer(id)
    }

     fun getPlayerInfo(id: Int): Player {
        return UrGameDao?.getPlayerInfo(id)
    }

     fun getPlayerWinner(winner: Boolean): Player {
        return UrGameDao?.getPlayerWinner(winner)
    }

    suspend fun deletePlayers() {
        UrGameDao?.deletePlayers()
    }


    suspend fun insertPiece(Piece: Piece) {
        UrGameDao?.updatePiece(Piece)
    }

    suspend fun getPieces():List<Piece>{
        return UrGameDao?.getPieces()

    }

    suspend fun insertBoard(Board: Board) {
        UrGameDao.updateBoard(Board)
    }

    fun getBoard():Board{
        return UrGameDao?.getBoard()
    }

    fun getBoardInfo():List<Board>{
        return UrGameDao?.getBoardInfo()
    }







}

