package com.example.royalgameofur.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.royalgameofur.model.Board
import com.example.royalgameofur.model.Piece
import com.example.royalgameofur.model.Player

@Dao
interface UrGameDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun updateBoard()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePlayer(Player: Player)

    @Query("SELECT * FROM PlayerTable WHERE id =:id")
    fun getPlayer(id: Int):LiveData<Player>

    @Query("SELECT * FROM PlayerTable WHERE id =:id")
    fun getPlayerInfo(id: Int): Player

    @Query("SELECT * FROM PlayerTable WHERE winner =:winner")
    fun getPlayerWinner(winner: Boolean): Player

    @Query("DELETE FROM playertable")
    suspend fun deletePlayers()



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBoard(Board: Board)

    @Query("SELECT * FROM BoardTable")
    fun getBoard(): Board


    @Query("SELECT * FROM BoardTable WHERE PLAYER != 0")
    fun getBoardInfo(): List<Board>




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePiece(Piece: Piece)

    @Query("SELECT * FROM PieceTable")
    suspend fun getPieces(): List<Piece>

}