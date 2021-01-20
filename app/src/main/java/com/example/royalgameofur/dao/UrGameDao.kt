package com.example.royalgameofur.dao

import androidx.lifecycle.LiveData
import androidx.room.*
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
    fun getScore(id: Int): Player


    @Query("DELETE FROM playertable")
    suspend fun deletePlayers()

}