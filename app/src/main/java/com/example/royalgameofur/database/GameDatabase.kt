package com.example.royalgameofur.database

import android.content.Context
import androidx.room.*
import com.example.royalgameofur.converter.Converters
import com.example.royalgameofur.dao.UrGameDao
import com.example.royalgameofur.model.Board
import com.example.royalgameofur.model.Piece
import com.example.royalgameofur.model.Player

@Database(entities = [Piece::class, Player::class, Board::class], version = 6, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameDatabase : RoomDatabase() {

    abstract fun UrGameDao(): UrGameDao

    companion object {
        private const val DATABASE_NAME = "GAME_DB  "

        @Volatile
        private var INSTANCE: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase? {
            if (INSTANCE == null) {
                synchronized(GameDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            GameDatabase::class.java,
                            DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }

    }

}
